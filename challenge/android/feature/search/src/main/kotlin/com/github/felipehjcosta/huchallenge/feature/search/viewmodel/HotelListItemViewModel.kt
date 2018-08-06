package com.github.felipehjcosta.huchallenge.feature.search.viewmodel

import com.github.felipehjcosta.huchallenge.base.hotels.Hotel
import java.text.NumberFormat

class HotelListItemViewModel(
        private val hotel: Hotel
) : ListItemViewModel {
    override val name: String?
        get() = hotel.name

    override val city: String?
        get() = hotel.address.city

    override val state: String?
        get() = hotel.address.state

    override val price: String?
        get() = NumberFormat.getCurrencyInstance().format(hotel.price.amount)

    override val amenity1: String?
        get() = hotel.amenities.getOrNull(0)?.name
    override val amenity2: String?
        get() = hotel.amenities.getOrNull(1)?.name
    override val amenity3: String?
        get() = hotel.amenities.getOrNull(2)?.name
}