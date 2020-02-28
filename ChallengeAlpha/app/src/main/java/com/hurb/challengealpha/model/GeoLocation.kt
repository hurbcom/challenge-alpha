package com.hurb.challengealpha.model

import com.google.gson.annotations.SerializedName

data class GeoLocation(

    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double
)