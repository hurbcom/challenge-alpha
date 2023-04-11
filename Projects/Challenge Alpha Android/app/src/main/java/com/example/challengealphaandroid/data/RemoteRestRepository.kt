package com.example.challengealphaandroid.data

import com.example.challengealphaandroid.model.StarshipAndPlanetSearchResponse


interface RemoteRestRepository {
    suspend fun fetchStarship(name: String): ResultRest<StarshipAndPlanetSearchResponse>
    suspend fun fetchPlanet(name: String): ResultRest<StarshipAndPlanetSearchResponse>
}