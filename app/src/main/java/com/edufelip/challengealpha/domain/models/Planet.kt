package com.edufelip.challengealpha.domain.models

data class Planet(
    val climate: String,
    val diameter: String,
    val gravity: String,
    val orbitalPeriod: String,
    val population: String,
    val rotationPeriod: String,
    val surfaceWater: String,
    val terrain: String,
    val url: String,
    val films: List<String>,
    val residents: List<String>,
)