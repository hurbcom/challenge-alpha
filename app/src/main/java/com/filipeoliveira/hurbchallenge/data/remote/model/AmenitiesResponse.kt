package com.filipeoliveira.hurbchallenge.data.remote.model

import com.google.gson.annotations.SerializedName

data class AmenitiesResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("category")
    val category: String
)
