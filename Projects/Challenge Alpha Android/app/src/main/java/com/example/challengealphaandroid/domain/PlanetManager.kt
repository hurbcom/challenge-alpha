package com.example.challengealphaandroid.domain

import com.example.challengealphaandroid.GetPlanetsQuery
import com.example.challengealphaandroid.common.BaseManager
import com.example.challengealphaandroid.common.Utils
import com.example.challengealphaandroid.data.RemoteApolloRepository
import com.example.challengealphaandroid.data.RemoteRestRepository
import com.example.challengealphaandroid.data.ResultRest
import com.example.challengealphaandroid.data.room.LocalPlanetRepository
import com.example.challengealphaandroid.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlanetManager @Inject constructor(
    val remoteApolloRepository: RemoteApolloRepository,
    val localPlanetRepository: LocalPlanetRepository,
    val remoteRestRepository: RemoteRestRepository
) :
    BaseManager() {

    override suspend fun getAllItens(): Flow<ResultWithStatus<List<Planet>>> = flow {
        val cache = loadCache()
        if (!cache.isEmpty())
            emit(ResultWithStatus.success(cache))
        val result = remoteApolloRepository.fetchList(GetPlanetsQuery())
        when (result) {
            is Result.Success -> {
                val data = result.data as? List<GetPlanetsQuery.Planet>
                val planet = data?.mapIndexed { index, it ->
                    Planet(
                        name = it.name ?: "unknow planet",
                        population = it.population,
                        diameter = it.diameter
                    )
                } ?: emptyList()
                planet.forEach {
                    it.id = getId(it.name)
                }
                val updatedWithFavorites = updatePeopleFavorites(cache, planet)
                localPlanetRepository.deleteAll()
                localPlanetRepository.savePlanet((updatedWithFavorites as List<Planet>))
                emit(ResultWithStatus.success(updatedWithFavorites))
            }
            is Result.Error -> emit(ResultWithStatus.error(result.message))
        }
    }

    suspend fun getId(name: String?): Int {
        val remote = remoteRestRepository.fetchPlanet(name ?: "")
        val url =
            if (remote.status == ResultRest.Status.SUCCESS) remote.data?.results?.get(0)?.url else null
        val id = Utils.extractNumberFromUrl(url ?: "")
        return id
    }

    suspend fun loadCache(): List<Planet> {
        return localPlanetRepository.getPlanetCache()
    }

    suspend fun updatePlanetFavorite(planet: Planet, isFavorite: Boolean) {
        localPlanetRepository.updatePlanet(planet = planet.apply { this.isFavorite = isFavorite })
    }
}