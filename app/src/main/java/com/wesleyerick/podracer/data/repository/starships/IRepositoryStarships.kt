package com.wesleyerick.podracer.data.repository.starships

import com.wesleyerick.podracer.data.model.starships.Starship
import com.wesleyerick.podracer.data.model.starships.StarshipsList
import retrofit2.Response

interface IRepositoryStarships {
    suspend fun getAll(): Response<StarshipsList>
    suspend fun getDetails(id: String): Response<Starship>
}