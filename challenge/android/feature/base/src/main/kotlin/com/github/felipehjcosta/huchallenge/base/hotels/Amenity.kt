package com.github.felipehjcosta.huchallenge.base.hotels

import com.google.gson.annotations.SerializedName

data class Amenity(
        @SerializedName("name") val name: String = ""
)