package com.edufelip.challengealpha.data.network.models

import com.google.gson.annotations.SerializedName

class PlanetResponse (
    @SerializedName("climate")
    val climate: String,
    @SerializedName("diameter")
    val diameter: String,
    @SerializedName("gravity")
    val gravity: String,
    @SerializedName("orbital_period")
    val orbitalPeriod: String,
    @SerializedName("population")
    val population: String,
    @SerializedName("rotation_period")
    val rotationPeriod: String,
    @SerializedName("surface_water")
    val surfaceWater: String,
    @SerializedName("terrain")
    val terrain: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("films")
    val films: List<String>,
    @SerializedName("residents")
    val residents: List<String>,
)