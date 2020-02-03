package br.com.flyingdutchman.challenge_alpha.data.model

import com.google.gson.annotations.SerializedName

data class QuantityDescriptors(
    @SerializedName("maxPeople")
    val maxPeople: Int,
    @SerializedName("nights")
    val nights: Int
)