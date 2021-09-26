package com.filipeoliveira.hurbchallenge.data.remote.model

import com.google.gson.annotations.SerializedName

data class HotelRequestResponse(
    @SerializedName("results")
    val result: List<HotelResponse>
)
