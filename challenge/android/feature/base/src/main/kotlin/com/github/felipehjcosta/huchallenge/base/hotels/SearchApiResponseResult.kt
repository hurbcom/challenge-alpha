package com.github.felipehjcosta.huchallenge.base.hotels

import com.google.gson.annotations.SerializedName

data class SearchApiResponseResult(
    @SerializedName("name") val name: String = ""
)