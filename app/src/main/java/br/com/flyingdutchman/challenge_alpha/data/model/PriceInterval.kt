package br.com.flyingdutchman.challenge_alpha.data.model

import com.google.gson.annotations.SerializedName

data class PriceInterval(
    @SerializedName("filterPattern")
    val filterPattern: String,
    @SerializedName("max")
    val max: Int,
    @SerializedName("min")
    val min: Int
)