package com.wesleyerick.podracer.data.service

import com.wesleyerick.podracer.data.model.ListData
import com.wesleyerick.podracer.data.model.vehicles.Vehicle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface VehiclesService {
    @GET("vehicles/")
    suspend fun getAll(): Response<ListData<Vehicle>>

    @GET("vehicles/{id}")
    suspend fun getDetails(@Path("id") id: String): Response<Vehicle>
}
