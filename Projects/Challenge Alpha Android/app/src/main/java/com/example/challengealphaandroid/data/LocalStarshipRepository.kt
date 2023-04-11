package com.example.challengealphaandroid.data

import com.example.challengealphaandroid.model.Starship

interface LocalStarshipRepository {
    suspend fun getStarshipCache(): List<Starship>
    suspend fun saveStarships(starships: List<Starship>)
    suspend fun saveStarships(starship: Starship)
    suspend fun updateStarship(starship: Starship)
    suspend fun deleteAll()
}