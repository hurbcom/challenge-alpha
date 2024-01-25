package com.wesleyerick.podracer.data.model.starships

import com.google.gson.annotations.SerializedName

data class Starship(
    @SerializedName("MGLT")
    val MGLT: String = String(),

    @SerializedName("cargo_capacity")
    val cargoCapacity: String = String(),

    @SerializedName("consumables")
    val consumables: String = String(),

    @SerializedName("cost_in_credits")
    val costInCredits: String = String(),

    @SerializedName("created")
    val created: String = String(),

    @SerializedName("crew")
    val crew: String = String(),

    @SerializedName("edited")
    val edited: String = String(),

    @SerializedName("films")
    val films: List<String> = emptyList(),

    @SerializedName("hyperdrive_rating")
    val hyperdriveRating: String = String(),

    @SerializedName("length")
    val length: String = String(),

    @SerializedName("manufacturer")
    val manufacturer: String = String(),

    @SerializedName("max_atmosphering_speed")
    val maxAtmospheringSpeed: String = String(),

    @SerializedName("model")
    val model: String = String(),

    @SerializedName("name")
    val name: String = String(),

    @SerializedName("passengers")
    val passengers: String = String(),

    @SerializedName("pilots")
    val pilots: List<String> = emptyList(),

    @SerializedName("starship_class")
    val starshipClass: String = String(),

    @SerializedName("url")
    val url: String = String()
)