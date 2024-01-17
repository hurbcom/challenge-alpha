package com.wesleyerick.podracer.data.model.vehicles

data class VehiclesList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Vehicle>
)