package com.isranascimento.hotelslist.ui.models

import com.isranascimento.hotelslist.models.Hotel

data class HotelListUIItem(
    val name: String
): HotelListUI() {
    companion object {
        fun fromDomainModel(domain: Hotel) = HotelListUIItem(
            name = domain.name
        )
    }
}