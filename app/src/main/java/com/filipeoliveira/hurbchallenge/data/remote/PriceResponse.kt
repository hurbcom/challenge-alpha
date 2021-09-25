package com.filipeoliveira.hurbchallenge.data.remote

import com.google.gson.annotations.SerializedName

data class PriceResponse(
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("amountPerDay")
    val amountPerDay: String?
)
