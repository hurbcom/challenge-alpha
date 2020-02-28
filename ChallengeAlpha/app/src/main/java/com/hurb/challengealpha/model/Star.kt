package com.hurb.challengealpha.model

import com.google.gson.annotations.SerializedName

data class Star(

    @SerializedName("term") val term: Int,
    @SerializedName("filter") val filter: String,
    @SerializedName("count") val count: Int
)