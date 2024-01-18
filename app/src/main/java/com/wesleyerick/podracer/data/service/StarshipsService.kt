package com.wesleyerick.podracer.data.service

import com.wesleyerick.podracer.data.model.ListData
import com.wesleyerick.podracer.data.model.starships.Starship
import com.wesleyerick.podracer.data.model.starships.StarshipsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StarshipsService {
    @GET("starships/")
    suspend fun getAll(): Response<ListData<Starship>>

    @GET("starships/{id}")
    suspend fun getDetails(@Path("id") id: String): Response<Starship>
}