package com.hotelurbano.desafio.listhotels.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Cristian on 17/12/2017.
 */
data class HotelAddress(
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String
) : Serializable