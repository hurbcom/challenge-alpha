package br.com.flyingdutchman.challenge_alpha.data.model

import com.google.gson.annotations.SerializedName

data class Amenity(
    @SerializedName("category")
    val category: String,
    @SerializedName("name")
    val name: String
)