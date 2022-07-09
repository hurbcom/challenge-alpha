package com.edufelip.challengealpha.data.models

import com.google.gson.annotations.SerializedName

class GeneralListMenuItemResponse(
    @SerializedName("title") val title: String,
    @SerializedName("image") val image: String,
    @SerializedName("resource_uri") val resourceUri: String,
)