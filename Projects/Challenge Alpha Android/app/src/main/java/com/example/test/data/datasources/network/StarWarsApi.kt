package com.example.test.data.datasources.network

import com.example.test.data.datasources.network.models.PersonResponse
import com.example.test.data.datasources.network.models.PlanetResponse
import com.example.test.data.datasources.network.models.StarshipResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsApi {
    @GET("people")
    suspend fun getPeople(
        @Query("page") page: Int,
        @Query("search") search: String
    ): StarWarsBaseResponse<PersonResponse>

    @GET("planets")
    suspend fun getPlanets(
        @Query("page") page: Int,
        @Query("search") search: String
    ): StarWarsBaseResponse<PlanetResponse>

    @GET("starships")
    suspend fun getStarships(
        @Query("page") page: Int,
        @Query("search") search: String
    ): StarWarsBaseResponse<StarshipResponse>
}