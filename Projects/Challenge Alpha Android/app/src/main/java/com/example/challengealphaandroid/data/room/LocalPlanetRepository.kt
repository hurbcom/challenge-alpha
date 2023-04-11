package com.example.challengealphaandroid.data.room

import com.example.challengealphaandroid.model.Planet

interface LocalPlanetRepository {
    suspend fun getPlanetCache(): List<Planet>
    suspend fun savePlanet(planet: List<Planet>)
    suspend fun savePlanet(planet: Planet)
    suspend fun updatePlanet(planet: Planet)
    suspend fun deleteAll()
}