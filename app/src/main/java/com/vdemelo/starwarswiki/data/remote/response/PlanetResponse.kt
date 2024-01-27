package com.vdemelo.starwarswiki.data.remote.response

import com.google.gson.annotations.SerializedName

class PlanetResponse(
    @SerializedName("climate") val climate: String?,
    @SerializedName("created") val created: String?,
    @SerializedName("diameter") val diameter: String?,
    @SerializedName("edited") val edited: String?,
    @SerializedName("films") val films: List<String?>?,
    @SerializedName("gravity") val gravity: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("orbital_period") val orbitalPeriod: String?,
    @SerializedName("population") val population: String?,
    @SerializedName("residents") val residents: List<String?>?,
    @SerializedName("rotation_period") val rotationPeriod: String?,
    @SerializedName("surface_water") val surfaceWater: String?,
    @SerializedName("terrain") val terrain: String?,
    @SerializedName("url") val url: String?
)
