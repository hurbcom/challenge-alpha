package com.wesleyerick.podracer.data.repository.starships

import com.wesleyerick.podracer.data.model.ListData
import com.wesleyerick.podracer.data.model.starships.Starship
import retrofit2.Response

interface IRepositoryStarships {
    suspend fun getAll(): Response<ListData<Starship>>
    suspend fun getDetails(id: String): Response<Starship>
}