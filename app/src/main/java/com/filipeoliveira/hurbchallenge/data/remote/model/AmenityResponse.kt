package com.filipeoliveira.hurbchallenge.data.remote.model

import com.google.gson.annotations.SerializedName

data class AmenityResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("filter")
    val filter: String
)
