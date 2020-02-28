package com.hurb.challengealpha.model

import com.google.gson.annotations.SerializedName

data class DepartureCity(

    @SerializedName("term") val term: String,
    @SerializedName("filter") val filter: String,
    @SerializedName("count") val count: Int
)