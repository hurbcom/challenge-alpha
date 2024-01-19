package com.wesleyerick.podracer.data.model.vehicles

import com.google.gson.annotations.SerializedName

data class Vehicle(

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
    val pilots: List<Any> = emptyList(),

    @SerializedName("url")
    val url: String = String(),

    @SerializedName("vehicle_class")
    val vehicleClass: String = String(),
)
