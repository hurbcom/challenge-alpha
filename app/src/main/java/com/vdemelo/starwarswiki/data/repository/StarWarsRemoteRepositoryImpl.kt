package com.vdemelo.starwarswiki.data.repository

import com.vdemelo.starwarswiki.data.remote.api.StarWarsApi
import com.vdemelo.starwarswiki.data.remote.response.SpeciesListResponse
import com.vdemelo.starwarswiki.data.remote.response.SpeciesResponse
import com.vdemelo.starwarswiki.domain.repository.StarWarsRemoteRepository

class StarWarsRemoteRepositoryImpl(
    private val api: StarWarsApi
): StarWarsRemoteRepository {

    override suspend fun fetchSpecies(page: Int, search: String?): SpeciesListResponse {
        return api.getSpecies(page = page, search = search)
    }

    override suspend fun fetchSpeciesDetails(speciesNumber: Int): SpeciesResponse {
        return api.getSpeciesDetails(speciesNumber)
    }

}
