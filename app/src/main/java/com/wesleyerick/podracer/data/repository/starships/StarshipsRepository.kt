package com.wesleyerick.podracer.data.repository.starships

import com.wesleyerick.podracer.data.model.ListData
import com.wesleyerick.podracer.data.model.starships.Starship
import com.wesleyerick.podracer.data.model.starships.StarshipsList
import com.wesleyerick.podracer.data.service.StarshipsService
import retrofit2.Response

class StarshipsRepository(private val service: StarshipsService): IRepositoryStarships {
    override suspend fun getAll(): Response<ListData<Starship>> = service.getAll()
    override suspend fun getDetails(id: String): Response<Starship> = service.getDetails(id)
}