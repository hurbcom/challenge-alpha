package com.vdemelo.starwarswiki.data.repository

import com.vdemelo.starwarswiki.data.remote.api.StarWarsApi
import com.vdemelo.starwarswiki.data.remote.response.SpeciesListResponse

class StarWarsRepository(
    private val api: StarWarsApi
) {

    suspend fun fetchSpecies(): SpeciesListResponse {
        return api.getSpecies()
    }

}
