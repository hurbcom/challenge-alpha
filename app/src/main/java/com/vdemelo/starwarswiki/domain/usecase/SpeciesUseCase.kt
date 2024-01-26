package com.vdemelo.starwarswiki.domain.usecase

import com.vdemelo.starwarswiki.domain.entity.RequestStatus
import com.vdemelo.starwarswiki.domain.entity.model.ItemsImageUrl
import com.vdemelo.starwarswiki.domain.entity.model.Species
import com.vdemelo.starwarswiki.domain.entity.model.SpeciesList
import com.vdemelo.starwarswiki.domain.entity.model.getImageUrl
import com.vdemelo.starwarswiki.domain.repository.StarWarsLocalRepository
import com.vdemelo.starwarswiki.domain.repository.StarWarsRemoteRepository

private const val UNKNOWN_ERROR_MESSAGE = "An unknown error occurred."

class SpeciesUseCase(
    private val remoteRepository: StarWarsRemoteRepository,
    private val localRepository: StarWarsLocalRepository //TODO se n for usar remover
) {

    suspend fun fetchSpecies(page: Int = 0, search: String? = null): RequestStatus<SpeciesList> {
        val response = try {
            remoteRepository.fetchSpecies(page, search)
        } catch (e: Exception) {
            return RequestStatus.Error(message = UNKNOWN_ERROR_MESSAGE)
        }
        val entity = SpeciesList(response)
        return RequestStatus.Success(data = entity)
    }

    suspend fun fetchSpeciesDetails(speciesNumber: Int): RequestStatus<Species> {
        val response = try {
            remoteRepository.fetchSpeciesDetails(speciesNumber)
        } catch (e: Exception) {
            return RequestStatus.Error(message = UNKNOWN_ERROR_MESSAGE)
        }
        val entity = Species(response)
        return RequestStatus.Success(data = entity)
    }

    // URL examples:
    // species.url = https://swapi.dev/api/species/1/
    // species image = https://starwars-visualguide.com/assets/img/species/1.jpg

    fun getSpeciesNumber(speciesUrl: String): Int? {
        val charsToDrop = if (speciesUrl.endsWith("/")) 1 else 0
        val number = try {
            speciesUrl.dropLast(charsToDrop).takeLastWhile { it.isDigit() }.toInt()
        } catch (e: Exception) {
            null
        }
        return number
    }

    fun getSpeciesImageUrl(speciesNumber: Int): String {
        return ItemsImageUrl.SPECIES.getImageUrl(speciesNumber.toString())
    }

}
