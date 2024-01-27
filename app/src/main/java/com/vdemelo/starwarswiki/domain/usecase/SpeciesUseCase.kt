package com.vdemelo.starwarswiki.domain.usecase

import com.vdemelo.starwarswiki.domain.DefaultErrors
import com.vdemelo.starwarswiki.domain.entity.RequestStatus
import com.vdemelo.starwarswiki.domain.entity.model.Species
import com.vdemelo.starwarswiki.domain.entity.model.SpeciesList
import com.vdemelo.starwarswiki.domain.repository.StarWarsRemoteRepository

class SpeciesUseCase(private val remoteRepository: StarWarsRemoteRepository) {

    suspend fun fetchSpecies(page: Int = 0, search: String? = null): RequestStatus<SpeciesList> {
        val response = try {
            remoteRepository.fetchSpecies(page, search)
        } catch (e: Exception) {
            return RequestStatus.Error(message = DefaultErrors.UNKNOWN_ERROR.message)
        }
        val entity = SpeciesList(response)
        return RequestStatus.Success(data = entity)
    }

    suspend fun fetchSpeciesDetails(speciesNumber: Int): RequestStatus<Species> {
        val response = try {
            remoteRepository.fetchSpeciesDetails(speciesNumber)
        } catch (e: Exception) {
            return RequestStatus.Error(message = DefaultErrors.UNKNOWN_ERROR.message)
        }
        val entity = Species(response)
        return RequestStatus.Success(data = entity)
    }

}
