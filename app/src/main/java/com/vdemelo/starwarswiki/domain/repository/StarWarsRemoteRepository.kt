package com.vdemelo.starwarswiki.domain.repository

import com.vdemelo.starwarswiki.data.remote.response.SpeciesListResponse

interface StarWarsRemoteRepository {

    suspend fun fetchSpecies(): SpeciesListResponse

}
