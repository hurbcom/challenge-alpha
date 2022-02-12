package com.isranascimento.hotelslist.mapper

import com.isranascimento.datatransferobjects.hotels.HotelResponse
import com.isranascimento.datatransferobjects.hotels.HotelsResponse
import com.isranascimento.hotelslist.models.Address
import com.isranascimento.hotelslist.models.Hotel

fun HotelsResponse.asDomainModel() = this.results?.map {
    it.asDomainModel()
}

private fun HotelResponse.asDomainModel(): Hotel {
    return Hotel(
        id = this.id ?: "",
        gallery = this.gallery?.map { it.url ?: ""} ?: listOf(),
        sku = this.sku ?: "",
        amenities = this.amenities?.map { it.name ?: "" } ?: listOf(),
        name = this.name ?: "",
        price = this.price?.currentPrice ?: Double.MAX_VALUE,
        address = Address(
            this.address?.state ?: "",
            this.address?.city ?: ""
        ),
        starCount = this.stars ?: 0
    )
}