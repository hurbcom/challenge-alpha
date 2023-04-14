package br.com.hurbandroidchallenge.data.remote.model

import com.google.gson.annotations.SerializedName

class PlanetDto(
    @SerializedName("name") val name: String,
    @SerializedName("rotation_period") val rotationPeriod: String,
    @SerializedName("orbital_period") val orbitalPeriod: String,
    @SerializedName("diameter") val diameter: String,
    @SerializedName("climate") val climate: String,
    @SerializedName("gravity") val gravity: String,
    @SerializedName("terrain") val terrain: String,
    @SerializedName("surface_water") val surfaceWater: String,
    @SerializedName("population") val population: String,
    @SerializedName("residents") val residents: List<String>,
    @SerializedName("films") val films: List<String>,
    @SerializedName("url") val url: String,
)