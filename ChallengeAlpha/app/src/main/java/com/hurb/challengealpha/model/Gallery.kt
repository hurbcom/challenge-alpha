package com.hurb.challengealpha.model

import com.google.gson.annotations.SerializedName

data class Gallery(

    @SerializedName("description") val description: String,
    @SerializedName("url") val url: String
)