package com.wesleyerick.podracer.data.model.vehicles

data class Vehicle(
    val cargoCapacity: String = String(),
    val consumables: String = String(),
    val costInCredits: String = String(),
    val created: String = String(),
    val crew: String = String(),
    val edited: String = String(),
    val films: List<String> = emptyList(),
    val length: String = String(),
    val manufacturer: String = String(),
    val maxAtmospheringSpeed: String = String(),
    val model: String = String(),
    val name: String = String(),
    val passengers: String = String(),
    val pilots: List<Any> = emptyList(),
    val url: String = String(),
    val vehicleClass: String = String(),
)
