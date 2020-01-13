package com.example.challenge_alpha.model

import com.google.gson.annotations.SerializedName


data class Suggestions(
    @field:SerializedName("text") val text: String? = null,
    @field:SerializedName("suggestionType") val suggestionType: String? = null,
    @field:SerializedName("country") val country: String? = null,
    @field:SerializedName("state") val state: String? = null,
    @field:SerializedName("city") val city: String? = null,
    @field:SerializedName("sku") val sku: String? = null,
    @field:SerializedName("filter") val filter: String,
    @field:SerializedName("url") val url: String? = null
)