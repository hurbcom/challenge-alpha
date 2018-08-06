package com.github.felipehjcosta.huchallenge.base.hotels

import com.google.gson.annotations.SerializedName

data class Image(
        @SerializedName("url") val url: String = ""
)