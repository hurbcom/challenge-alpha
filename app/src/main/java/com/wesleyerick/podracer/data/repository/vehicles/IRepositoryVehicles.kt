package com.wesleyerick.podracer.data.repository.vehicles

import com.wesleyerick.podracer.data.model.ListData
import com.wesleyerick.podracer.data.model.vehicles.Vehicle
import retrofit2.Response

interface IRepositoryVehicles {
    suspend fun getAll(): Response<ListData<Vehicle>>
    suspend fun getDetails(id: String): Response<Vehicle>
}
