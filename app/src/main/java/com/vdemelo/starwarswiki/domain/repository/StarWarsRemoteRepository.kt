package com.vdemelo.starwarswiki.domain.repository

import com.vdemelo.starwarswiki.data.remote.response.SpeciesListResponse
import com.vdemelo.starwarswiki.data.remote.response.SpeciesResponse

interface StarWarsRemoteRepository {

    suspend fun fetchSpecies(page: Int): SpeciesListResponse

    suspend fun fetchSpeciesDetails(speciesNumber: Int): SpeciesResponse

}
