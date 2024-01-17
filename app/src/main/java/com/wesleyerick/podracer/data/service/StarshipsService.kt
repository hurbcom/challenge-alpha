package com.wesleyerick.podracer.data.service

import com.wesleyerick.podracer.data.model.vehicles.Vehicle
import com.wesleyerick.podracer.data.model.vehicles.VehiclesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StarshipsService {
    @GET("starships/")
    suspend fun getAll(): Response<VehiclesList>

    @GET("starships/{id}")
    suspend fun getDetails(@Path("id") id: String): Response<Vehicle>
}