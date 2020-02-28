package com.hurb.challengealpha.model

import com.google.gson.annotations.SerializedName

data class PriceGroup(

    @SerializedName("min") val min: Int,
    @SerializedName("maxExclusive") val maxExclusive: Int,
    @SerializedName("filter") val filter: String,
    @SerializedName("count") val count: Int
)