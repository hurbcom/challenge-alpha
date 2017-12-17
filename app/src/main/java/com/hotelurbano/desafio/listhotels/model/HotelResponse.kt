package com.hotelurbano.desafio.listhotels.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Cristian on 17/12/2017.
 */
data class HotelResponse(
    @SerializedName("results") val results: List<HotelItem>?
) : Serializable