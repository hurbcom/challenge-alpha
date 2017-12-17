package com.hotelurbano.desafio.listhotels.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Cristian on 17/12/2017.
 */
data class HotelItem(
    @SerializedName("amenities") val amenities: List<HotelAmenity>,
    @SerializedName("id") val id: String,
    @SerializedName("price") val price: HotelPrice,
    @SerializedName("image") val image: String?,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("stars") val stars: Int?,
    @SerializedName("gallery") val gallery: List<HotelGallery>,
    @SerializedName("address") val address: HotelAddress
) : Serializable