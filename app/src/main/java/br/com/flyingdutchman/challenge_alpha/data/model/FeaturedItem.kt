package br.com.flyingdutchman.challenge_alpha.data.model

import com.google.gson.annotations.SerializedName

data class FeaturedItem(
    @SerializedName("description")
    val description: String,
    @SerializedName("hasInternet")
    val hasInternet: Boolean,
    @SerializedName("hasParking")
    val hasParking: Boolean
)