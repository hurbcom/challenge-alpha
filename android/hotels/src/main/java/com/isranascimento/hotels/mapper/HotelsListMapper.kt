package com.isranascimento.hotels.mapper

import com.isranascimento.networkdtos.hotels.HotelResponse
import com.isranascimento.networkdtos.hotels.HotelsResponse
import com.isranascimento.core.models.hotel.Address
import com.isranascimento.core.models.hotel.Hotel

fun HotelsResponse.asDomainModel() = this.results.map {
    it.asDomainModel()
}

private fun HotelResponse.asDomainModel(): Hotel {
    return Hotel(
        id = this.id,
        gallery = this.gallery.map { it.url },
        amenities = this.amenities.map { it.name },
        name = this.name,
        address = Address(
            this.address.state,
            this.address.city
        ),
        starCount = this.stars,
        mainImage = this.image,
        description = this.description,
        url = this.url
    )
}