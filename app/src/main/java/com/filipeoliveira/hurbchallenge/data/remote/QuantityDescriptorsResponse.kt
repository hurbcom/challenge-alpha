package com.filipeoliveira.hurbchallenge.data.remote

import com.google.gson.annotations.SerializedName

data class QuantityDescriptorsResponse(
    @SerializedName("maxChildren")
    val maxChildren: Int?,
    @SerializedName("maxAdults")
    val maxAdults: Int?,
    @SerializedName("maxFreeChildrenAge")
    val maxFreeChildrenAge: Int?
)
