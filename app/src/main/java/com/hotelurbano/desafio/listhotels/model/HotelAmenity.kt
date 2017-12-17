package com.hotelurbano.desafio.listhotels.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Cristian on 17/12/2017.
 */
data class HotelAmenity(
    @SerializedName("name") val name: String,
    @SerializedName("category") val category: String
) : Serializable