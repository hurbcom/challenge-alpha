package br.com.flyingdutchman.challenge_alpha.data.model

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("geoLocation")
    val geoLocation: GeoLocation,
    @SerializedName("state")
    val state: String
)