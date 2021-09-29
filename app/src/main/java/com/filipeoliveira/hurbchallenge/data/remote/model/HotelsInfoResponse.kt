package com.filipeoliveira.hurbchallenge.data.remote.model

import com.google.gson.annotations.SerializedName

data class HotelsInfoResponse(
    @SerializedName("results")
    val result: List<HotelResponse>,
    @SerializedName("filters")
    val filters: FilterResponse
)
