package com.hurb.challengealpha.model

import com.google.gson.annotations.SerializedName

data class PriceInterval(

    @SerializedName("min") val min: Int,
    @SerializedName("max") val max: Int,
    @SerializedName("filterPattern") val filterPattern: String
)