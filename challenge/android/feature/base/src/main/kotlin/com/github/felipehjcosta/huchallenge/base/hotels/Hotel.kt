package com.github.felipehjcosta.huchallenge.base.hotels

import com.google.gson.annotations.SerializedName

data class Hotel(
    @SerializedName("name") val name: String = "",
    @SerializedName("stars") val stars: Int = -1,
    @SerializedName("isHotel") val isHotel: Boolean = false
)