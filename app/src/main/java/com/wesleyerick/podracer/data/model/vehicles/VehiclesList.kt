package com.wesleyerick.podracer.data.model.vehicles

data class VehiclesList(
    val count: Int = 0,
    val next: String = String(),
    val previous: String? = String(),
    val vehiclesList: List<Vehicle> = emptyList()
)