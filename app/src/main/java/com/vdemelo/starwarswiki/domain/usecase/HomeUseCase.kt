package com.vdemelo.starwarswiki.domain.usecase

import com.vdemelo.starwarswiki.domain.entity.RequestStatus
import com.vdemelo.starwarswiki.domain.entity.model.SpeciesList
import com.vdemelo.starwarswiki.domain.repository.StarWarsLocalRepository
import com.vdemelo.starwarswiki.domain.repository.StarWarsRemoteRepository
import java.time.LocalDateTime

private const val UNKNOWN_ERROR_MESSAGE = "An unknown error occurred."

class HomeUseCase(
    private val remoteRepository: StarWarsRemoteRepository,
    private val localRepository: StarWarsLocalRepository
) {

    suspend fun fetchSpecies(): RequestStatus<SpeciesList> {
        val response = try {
            remoteRepository.fetchSpecies()
        } catch (e: Exception) {
            return RequestStatus.Error(message = UNKNOWN_ERROR_MESSAGE)
        }
        val entity = SpeciesList(response)
        return RequestStatus.Success(data = entity)
    }

}
