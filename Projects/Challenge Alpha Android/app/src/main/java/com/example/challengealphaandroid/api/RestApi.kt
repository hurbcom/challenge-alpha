package com.example.challengealphaandroid.api

import com.example.challengealphaandroid.model.StarshipAndPlanetSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {
    @GET("starships/")
    suspend fun getStarshipId(
        @Query("search") name: String
    ): Response<StarshipAndPlanetSearchResponse>

    @GET("planets/")
    suspend fun getPlanetId(
        @Query("search") name: String
    ): Response<StarshipAndPlanetSearchResponse>
}