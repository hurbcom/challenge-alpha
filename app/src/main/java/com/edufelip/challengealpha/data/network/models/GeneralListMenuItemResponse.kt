package com.edufelip.challengealpha.data.network.models

import com.google.gson.annotations.SerializedName

class GeneralListMenuItemResponse(
    @SerializedName("title") val title: String,
    @SerializedName("image") val image: String,
    @SerializedName("type") val type: String,
    @SerializedName("resource_uri") val resourceUri: String,
)