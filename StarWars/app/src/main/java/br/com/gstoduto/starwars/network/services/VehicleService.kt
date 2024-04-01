package br.com.gstoduto.starwars.network.services

import br.com.gstoduto.starwars.network.model.vehicle.VehiclesResponse
import retrofit2.http.GET

interface VehicleService {

    @GET("vehicles")
    suspend fun findAll(): VehiclesResponse
}