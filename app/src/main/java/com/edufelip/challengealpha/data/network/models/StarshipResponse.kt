package com.edufelip.challengealpha.data.network.models

import com.google.gson.annotations.SerializedName

class StarshipResponse (
    @SerializedName("MGLT")
    val mglt: String,
    @SerializedName("cargo_capacity")
    val cargoCapacity: String,
    @SerializedName("consumables")
    val consumables: String,
    @SerializedName("cost_in_credits")
    val costInCredits: String,
    @SerializedName("crew")
    val crew: String,
    @SerializedName("hyperdrive_rating")
    val hyperdriveRating: String,
    @SerializedName("length")
    val length: String,
    @SerializedName("manufacturer")
    val manufacturer: String,
    @SerializedName("max_atmosphering_speed")
    val maxAtmospheringSpeed: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("starship_class")
    val starshipClass: String,
    @SerializedName("passengers")
    val passengers: String,
    @SerializedName("films")
    val films: List<String>,
    @SerializedName("pilots")
    val pilots: List<String>,
)