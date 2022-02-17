package com.isranascimento.theme.hotel

import com.isranascimento.coremodels.hotel.Hotel

data class HotelCardItem(
    val id: String,
    val name: String,
    val image: String,
    val city: String,
    val state: String,
    val amenities: List<String>
) {

    fun areItemsTheSame(other: HotelCardItem): Boolean {
        return this.id == other.id
    }

    fun areContentTheSame(other: HotelCardItem): Boolean {
        return this.name == other.name &&
                this.amenities == other.amenities &&
                this.city == other.city &&
                this.image == other.image &&
                this.state == other.state
    }

    companion object {
        private const val AMENITIES_TO_DISPLAY_ON_LIST_COUNT = 3
        fun fromDomainModel(domain: Hotel) = HotelCardItem(
            name = domain.name,
            id = domain.id,
            image = domain.mainImage,
            city = domain.address.city,
            state = domain.address.state,
            amenities = domain.amenities.take(AMENITIES_TO_DISPLAY_ON_LIST_COUNT)
        )
    }
}