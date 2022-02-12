package com.isranascimento.hotelslist.ui.viewmodels.models

import com.isranascimento.hotelslist.models.Hotel

data class HotelListUIItem(
    val name: String
) {
    companion object {
        fun fromDomainModel(domain: Hotel) = HotelListUIItem(
            name = domain.name
        )
    }
}