package com.filipeoliveira.hurbchallenge.ui.model

import java.io.Serializable

data class HotelUI(
    val id: String,
    val smallDescription: String,
    val amenities: List<AmenityUI>,
    val priceCurrency: PriceUI,
    val huFreeCancellation: Boolean,
    val image: String,
    val name: String,
    val url: String,
    val description: String,
    val stars: Int,
    val images: List<ImageUI>,
    val tags: List<String>,
    val quantityDescriptors: QuantityDescriptorsUI,
    val address: AddressUI
) : Serializable {

    fun hasBarAmenity(): Boolean {
        return amenities.find {
            it.name == AmenityUI.AMENITY_BAR
        } != null
    }

    fun hasWheelchairAmenity(): Boolean {
        return amenities.find {
            it.name == AmenityUI.AMENITY_WHEELCHAIR
        } != null
    }
    fun hasTvAmenity(): Boolean {
        return amenities.find {
            it.name == AmenityUI.AMENITY_TV
        } != null
    }
    fun hasToiletAmenity(): Boolean {
        return amenities.find {
            it.name == AmenityUI.AMENITY_TOILET
        } != null
    }
    fun hasParkingAmenity(): Boolean {
        return amenities.find {
            it.name == AmenityUI.AMENITY_PARKING
        } != null
    }
    fun hasWifiAmenity(): Boolean {
        return amenities.find {
            it.name == AmenityUI.AMENITY_WIFI
        } != null
    }
    fun hasPoolAmenity(): Boolean {
        return amenities.find {
            it.name == AmenityUI.AMENITY_POOL
        } != null
    }
    fun hasReceptionAmenity(): Boolean {
        return amenities.find {
            it.name == AmenityUI.AMENITY_RECEPTION
        } != null
    }
    fun hasRestaurantAmenity(): Boolean {
        return amenities.find {
            it.name == AmenityUI.AMENITY_RESTAURANT
        } != null
    }
    fun hasGymAmenity(): Boolean {
        return amenities.find {
            it.name == AmenityUI.AMENITY_GYM
        } != null
    }

    fun getAddressCityAndState(): String = address.getCityAndStateAsString()

}
