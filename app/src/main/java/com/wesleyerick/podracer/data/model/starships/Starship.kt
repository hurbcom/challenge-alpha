package com.wesleyerick.podracer.data.model.starships

data class Starship(
    val MGLT: String = String(),
    val cargo_capacity: String = String(),
    val consumables: String = String(),
    val cost_in_credits: String = String(),
    val created: String = String(),
    val crew: String = String(),
    val edited: String = String(),
    val films: List<String> = emptyList(),
    val hyperdrive_rating: String = String(),
    val length: String = String(),
    val manufacturer: String = String(),
    val max_atmosphering_speed: String = String(),
    val model: String = String(),
    val name: String = String(),
    val passengers: String = String(),
    val pilots: List<String> = emptyList(),
    val starship_class: String = String(),
    val url: String = String()
)