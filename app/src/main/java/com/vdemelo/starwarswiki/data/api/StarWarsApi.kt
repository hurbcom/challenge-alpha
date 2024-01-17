package com.vdemelo.starwarswiki.data.api

import com.vdemelo.starwarswiki.data.remote.response.PlanetsListResponse
import com.vdemelo.starwarswiki.data.remote.response.SpeciesListResponse
import retrofit2.http.GET

interface StarWarsApi {

    @GET("planets")
    suspend fun getPlanets(): PlanetsListResponse

    @GET("species")
    suspend fun getSpecies(): SpeciesListResponse

}
