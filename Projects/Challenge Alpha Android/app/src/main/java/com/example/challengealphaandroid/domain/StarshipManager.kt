package com.example.challengealphaandroid.domain

import com.example.challengealphaandroid.GetStarshipsQuery
import com.example.challengealphaandroid.common.BaseManager
import com.example.challengealphaandroid.common.Utils
import com.example.challengealphaandroid.data.LocalStarshipRepository
import com.example.challengealphaandroid.data.RemoteApolloRepository
import com.example.challengealphaandroid.data.RemoteRestRepository
import com.example.challengealphaandroid.data.ResultRest
import com.example.challengealphaandroid.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StarshipManager @Inject constructor(val remoteApolloRepository: RemoteApolloRepository, val localStarshipRepository: LocalStarshipRepository, val remoteRestRepository: RemoteRestRepository):
    BaseManager() {

    override suspend fun getAllItens(): Flow<ResultWithStatus<List<Starship>>> = flow {
        val cache = loadCache()
        if(!cache.isEmpty())
            emit(ResultWithStatus.success(cache))
        val result = remoteApolloRepository.fetchList(GetStarshipsQuery())
        when (result) {
            is Result.Success -> {
                val data = result.data as? List<GetStarshipsQuery.Starship>
                val starship = data?.mapIndexed { index, it ->
                    Starship(
                        name = it.name ?: "unknow starship",
                        model = it.model,
                        starshipClass = it.starshipClass
                    )
                } ?: emptyList()
                starship.forEach {
                   it.id = getId(it.name)
                }
                val updatedWithFavorites = updatePeopleFavorites(cache, starship)
                localStarshipRepository.deleteAll()
                localStarshipRepository.saveStarships((updatedWithFavorites as List<Starship>))
                emit(ResultWithStatus.success(updatedWithFavorites))
            }
            is Result.Error -> emit(ResultWithStatus.error(result.message))
        }
    }

    private suspend fun getId(name: String?): Int {
        val remote = remoteRestRepository.fetchStarship(name ?: "")
        val url = if (remote.status == ResultRest.Status.SUCCESS) remote.data?.results?.get(0)?.url else null
        val id = Utils.extractNumberFromUrl(url ?: "")
        return id
    }

    suspend fun loadCache(): List<Starship> {
        return localStarshipRepository.getStarshipCache()
    }

    suspend fun updateStarshipFavorite(starship: Starship, isFavorite: Boolean) {
        localStarshipRepository.updateStarship(starship = starship.apply { this.isFavorite = isFavorite })
    }
}