package com.example.challengealphaandroid.model

import com.google.gson.annotations.SerializedName

data class StarshipAndPlanetSearchResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<ElementRest>
)

data class ElementRest(
    @SerializedName("url")
    val url: String
)


