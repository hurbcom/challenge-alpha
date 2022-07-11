package com.edufelip.challengealpha.domain.models

data class Vehicle(
    val id: String,
    val cargoCapacity: String,
    val consumables: String,
    val costInCredits: String,
    val crew: String,
    val length: String,
    val manufacturer: String,
    val maxAtmospheringSpeed: String,
    val model: String,
    val url: String,
    val name: String,
    val passengers: String,
    val vehicleClass: String,
    val films: List<String>,
    val pilots: List<String>,
    val imageUrl: String,
)