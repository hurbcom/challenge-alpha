package com.github.felipehjcosta.huchallenge.base.hotels

import com.google.gson.annotations.SerializedName

data class Address(
        @SerializedName("state") val state: String = "",
        @SerializedName("city") val city: String = ""
)