package com.example.test.data.datasources.network.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StarshipResponse(
    @Json(name = "cargo_capacity")
    val cargoCapacity: String?,
    @Json(name = "consumables")
    val consumables: String?,
    @Json(name = "cost_in_credits")
    val costInCredits: String?,
    @Json(name = "created")
    val created: String?,
    @Json(name = "crew")
    val crew: String?,
    @Json(name = "edited")
    val edited: String?,
    @Json(name = "films")
    val films: List<String>?,
    @Json(name = "hyperdrive_rating")
    val hyperdriveRating: String?,
    @Json(name = "length")
    val length: String?,
    @Json(name = "MGLT")
    val mGLT: String?,
    @Json(name = "manufacturer")
    val manufacturer: String?,
    @Json(name = "max_atmosphering_speed")
    val maxAtmospheringSpeed: String?,
    @Json(name = "model")
    val model: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "passengers")
    val passengers: String?,
    @Json(name = "pilots")
    val pilots: List<String>?,
    @Json(name = "starship_class")
    val starshipClass: String?,
    @Json(name = "url")
    val url: String?
)