package com.hurb.challengealpha.model

import com.google.gson.annotations.SerializedName

data class Suggestion(

    @SerializedName("text") val text: String,
    @SerializedName("suggestionType") val suggestionType: String,
    @SerializedName("country") val country: String,
    @SerializedName("state") val state: String,
    @SerializedName("city") val city: String,
    @SerializedName("filter") val filter: String,
    @SerializedName("url") val url: String
)