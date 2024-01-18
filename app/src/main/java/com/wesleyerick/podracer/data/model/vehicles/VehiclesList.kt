package com.wesleyerick.podracer.data.model.vehicles

import com.google.gson.annotations.SerializedName

data class VehiclesList(
    val count: Int = 0,
    val next: String = String(),
    val previous: String? = String(),
    @SerializedName("results")
    val vehiclesList: List<Vehicle> = emptyList()
)