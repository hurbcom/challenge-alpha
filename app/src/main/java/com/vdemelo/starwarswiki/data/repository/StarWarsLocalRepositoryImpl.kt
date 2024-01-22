package com.vdemelo.starwarswiki.data.repository

import com.vdemelo.starwarswiki.data.remote.response.SpeciesListResponse
import com.vdemelo.starwarswiki.domain.repository.StarWarsLocalRepository

class StarWarsLocalRepositoryImpl(): StarWarsLocalRepository {

    override suspend fun fetchSpecies(): SpeciesListResponse {
        //TODO - buscar no room ou no shared pref
        return SpeciesListResponse(null, null, null, null)
    }

}
