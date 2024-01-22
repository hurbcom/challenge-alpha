package com.vdemelo.starwarswiki.data.repository

import com.vdemelo.starwarswiki.data.remote.api.StarWarsApi
import com.vdemelo.starwarswiki.data.remote.response.SpeciesListResponse
import com.vdemelo.starwarswiki.domain.repository.StarWarsRemoteRepository

class StarWarsRemoteRepositoryImpl(
    private val api: StarWarsApi
): StarWarsRemoteRepository {

    override suspend fun fetchSpecies(): SpeciesListResponse {
        return api.getSpecies()
    }

}
