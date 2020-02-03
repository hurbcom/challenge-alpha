package br.com.flyingdutchman.challenge_alpha.data.model

import com.google.gson.annotations.SerializedName

data class ProductType(
    @SerializedName("count")
    val count: Int,
    @SerializedName("filter")
    val filter: String,
    @SerializedName("term")
    val term: String
)