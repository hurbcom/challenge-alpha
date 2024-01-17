package com.wesleyerick.podracer.data.service

import com.wesleyerick.podracer.data.model.vehicles.Vehicle
import com.wesleyerick.podracer.data.model.vehicles.VehiclesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface VehiclesService {
    @GET("vehicles/")
    suspend fun getAll(): Response<VehiclesList>

    @GET("vehicles/{id}")
    suspend fun getDetails(@Path("id") id: String): Response<Vehicle>
}
