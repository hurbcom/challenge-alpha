package com.hurb.challengealpha.model

import com.google.gson.annotations.SerializedName

data class Amenity(

    @SerializedName("name") val name: String,
    @SerializedName("category") val category: String
)