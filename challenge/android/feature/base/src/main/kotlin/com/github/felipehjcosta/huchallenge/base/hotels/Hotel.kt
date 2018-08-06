package com.github.felipehjcosta.huchallenge.base.hotels

import com.google.gson.annotations.SerializedName

data class Hotel(
    @SerializedName("name") val name: String = "",
    @SerializedName("stars") val stars: Int = -1,
    @SerializedName("isHotel") val isHotel: Boolean = false,
    @SerializedName("isPackage") val isPackage: Boolean = false,
    @SerializedName("gallery") val gallery: List<Image> = emptyList(),
    @SerializedName("amenities") val amenities: List<Amenity> = emptyList(),
    @SerializedName("address") val address: Address = Address(),
    @SerializedName("price") val price: Price = Price()
)