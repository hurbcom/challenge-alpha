package com.filipeoliveira.hurbchallenge.data.remote.model

import com.google.gson.annotations.SerializedName

data class FilterAmenityResponse(
    @SerializedName("term")
    val name: String,
    @SerializedName("filter")
    val filter: String
)
