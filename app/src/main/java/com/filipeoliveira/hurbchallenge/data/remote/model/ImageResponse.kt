package com.filipeoliveira.hurbchallenge.data.remote.model

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String?
)
