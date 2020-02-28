package com.hurb.challengealpha.model

import com.google.gson.annotations.SerializedName

data class QuantityDescriptor(

    @SerializedName("maxChildren") val maxChildren: Int,
    @SerializedName("maxAdults") val maxAdults: Int,
    @SerializedName("maxFreeChildrenAge") val maxFreeChildrenAge: Int
)