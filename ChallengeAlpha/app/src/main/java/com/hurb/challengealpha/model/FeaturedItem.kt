package com.hurb.challengealpha.model

import com.google.gson.annotations.SerializedName

data class FeaturedItem(

    @SerializedName("amenities") val amenities: List<String>,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("description") val description: String
)