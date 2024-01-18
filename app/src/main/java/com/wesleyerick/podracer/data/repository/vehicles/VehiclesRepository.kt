package com.wesleyerick.podracer.data.repository.vehicles

import com.wesleyerick.podracer.data.model.ListData
import com.wesleyerick.podracer.data.model.vehicles.Vehicle
import com.wesleyerick.podracer.data.model.vehicles.VehiclesList
import com.wesleyerick.podracer.data.service.VehiclesService
import retrofit2.Response

class VehiclesRepository(
    private val service: VehiclesService,
) : IRepositoryVehicles {
    override suspend fun getAll(): Response<ListData<Vehicle>> = service.getAll()
    override suspend fun getDetails(id: String): Response<Vehicle> = service.getDetails(id)
}
