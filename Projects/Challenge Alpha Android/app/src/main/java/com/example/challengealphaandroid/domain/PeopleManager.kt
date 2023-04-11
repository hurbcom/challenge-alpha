package com.example.challengealphaandroid.domain

import com.example.challengealphaandroid.GetPeopleQuery
import com.example.challengealphaandroid.common.BaseManager
import com.example.challengealphaandroid.common.BaseModel
import com.example.challengealphaandroid.data.LocalPersonRepository
import com.example.challengealphaandroid.data.RemoteApolloRepository
import com.example.challengealphaandroid.model.Homeworld
import com.example.challengealphaandroid.model.Person
import com.example.challengealphaandroid.model.Result
import com.example.challengealphaandroid.model.ResultWithStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PeopleManager @Inject constructor(
    val remoteApolloRepository: RemoteApolloRepository,
    val localPersonRepository: LocalPersonRepository
) : BaseManager() {

    override suspend fun getAllItens(): Flow<ResultWithStatus<List<Person>>> = flow {
        val cache = loadCache()
        if(!cache.isEmpty())
            emit(ResultWithStatus.success(cache))
        val result = remoteApolloRepository.fetchList(GetPeopleQuery())
        when (result) {
            is Result.Success -> {
                val data = result.data as? List<GetPeopleQuery.Person>
                val people = data?.mapIndexed { index, it ->
                    Person(
                        it.name ?: "unknow character",
                        it.birthYear,
                        Homeworld(it.homeworld?.name),
                        if (index < 15) index + 1 else index + 2
                    )
                } ?: emptyList()
                val updatedWithFavorites = updatePeopleFavorites(cache, people)
                localPersonRepository.deleteAll()
                localPersonRepository.savePeople((updatedWithFavorites as List<Person>))
                emit(ResultWithStatus.success(updatedWithFavorites))
            }
            is Result.Error -> emit(ResultWithStatus.error(result.message))
        }
    }

    suspend fun loadCache(): List<Person> {
        return localPersonRepository.getPeopleCache()
    }

    suspend fun updatePersonFavorite(person: Person, isFavorite: Boolean) {
        localPersonRepository.updatePerson(person = person.apply { this.isFavorite = isFavorite })
    }

}