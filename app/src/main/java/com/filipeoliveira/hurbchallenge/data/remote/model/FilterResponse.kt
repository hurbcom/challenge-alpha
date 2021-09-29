package com.filipeoliveira.hurbchallenge.data.remote.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FilterResponse(
    @SerializedName("amenities")
    val amenities: List<FilterAmenityResponse>?
): Serializable
