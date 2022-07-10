package com.edufelip.challengealpha.domain.models

data class Starship(
    val mglt: String,
    val cargoCapacity: String,
    val consumables: String,
    val costInCredits: String,
    val crew: String,
    val hyperdriveRating: String,
    val length: String,
    val manufacturer: String,
    val maxAtmospheringSpeed: String,
    val model: String,
    val url: String,
    val name: String,
    val starshipClass: String,
    val passengers: String,
    val films: List<String>,
    val pilots: List<String>,
)