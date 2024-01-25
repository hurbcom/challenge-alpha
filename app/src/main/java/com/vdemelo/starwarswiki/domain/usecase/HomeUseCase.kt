package com.vdemelo.starwarswiki.domain.usecase

import com.vdemelo.starwarswiki.domain.entity.RequestStatus
import com.vdemelo.starwarswiki.domain.entity.model.SpeciesList
import com.vdemelo.starwarswiki.domain.repository.StarWarsLocalRepository
import com.vdemelo.starwarswiki.domain.repository.StarWarsRemoteRepository

private const val UNKNOWN_ERROR_MESSAGE = "An unknown error occurred."

class HomeUseCase(
    private val remoteRepository: StarWarsRemoteRepository,
    private val localRepository: StarWarsLocalRepository //TODO se n for usar remover
) {

    suspend fun fetchSpecies(page: Int = 0): RequestStatus<SpeciesList> {
        val response = try {
            remoteRepository.fetchSpecies(page)
        } catch (e: Exception) {
            return RequestStatus.Error(message = UNKNOWN_ERROR_MESSAGE)
        }
        val entity = SpeciesList(response)
        return RequestStatus.Success(data = entity)
    }

}
