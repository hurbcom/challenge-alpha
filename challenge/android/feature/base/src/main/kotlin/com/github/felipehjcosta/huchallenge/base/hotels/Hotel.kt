package com.github.felipehjcosta.huchallenge.base.hotels

import com.google.gson.annotations.SerializedName

data class Hotel(
    @SerializedName("name") val name: String = ""
)