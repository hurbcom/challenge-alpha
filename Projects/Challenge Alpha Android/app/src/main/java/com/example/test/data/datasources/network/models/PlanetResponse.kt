package com.example.test.data.datasources.network.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlanetResponse(
    @Json(name = "climate")
    val climate: String?,
    @Json(name = "created")
    val created: String?,
    @Json(name = "diameter")
    val diameter: String?,
    @Json(name = "edited")
    val edited: String?,
    @Json(name = "films")
    val films: List<String>?,
    @Json(name = "gravity")
    val gravity: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "orbital_period")
    val orbitalPeriod: String?,
    @Json(name = "population")
    val population: String?,
    @Json(name = "residents")
    val residents: List<String>?,
    @Json(name = "rotation_period")
    val rotationPeriod: String?,
    @Json(name = "surface_water")
    val surfaceWater: String?,
    @Json(name = "terrain")
    val terrain: String?,
    @Json(name = "url")
    val url: String?
)