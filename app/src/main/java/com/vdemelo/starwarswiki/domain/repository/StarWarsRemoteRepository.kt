package com.vdemelo.starwarswiki.domain.repository

import com.vdemelo.starwarswiki.data.remote.response.PlanetResponse
import com.vdemelo.starwarswiki.data.remote.response.PlanetsListResponse
import com.vdemelo.starwarswiki.data.remote.response.SpeciesListResponse
import com.vdemelo.starwarswiki.data.remote.response.SpeciesResponse

interface StarWarsRemoteRepository {

    suspend fun fetchSpecies(page: Int, search: String?): SpeciesListResponse

    suspend fun fetchSpeciesDetails(id: Int): SpeciesResponse

    suspend fun fetchPlanets(page: Int, search: String?): PlanetsListResponse

    suspend fun fetchPlanetDetails(id: Int): PlanetResponse

}
