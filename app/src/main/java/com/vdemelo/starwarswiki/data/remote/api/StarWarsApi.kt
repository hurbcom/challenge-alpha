package com.vdemelo.starwarswiki.data.remote.api

import com.vdemelo.starwarswiki.data.remote.response.PlanetResponse
import com.vdemelo.starwarswiki.data.remote.response.PlanetsListResponse
import com.vdemelo.starwarswiki.data.remote.response.SpeciesListResponse
import com.vdemelo.starwarswiki.data.remote.response.SpeciesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsApi {

    @GET("species")
    suspend fun getSpecies(
        @Query("page") page: Int? = null,
        @Query("search") search: String? = null
    ): SpeciesListResponse

    @GET("species/{id}")
    suspend fun getSpeciesDetails(@Path("id") id: Int): SpeciesResponse

    @GET("planets")
    suspend fun getPlanets(
        @Query("page") page: Int? = null,
        @Query("search") search: String? = null
    ): PlanetsListResponse

    @GET("planets/{id}")
    suspend fun getPlanetDetails(@Path("id") id: Int): PlanetResponse


}
