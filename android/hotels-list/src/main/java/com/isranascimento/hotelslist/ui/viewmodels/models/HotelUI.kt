package com.isranascimento.hotelslist.ui.viewmodels.models

import com.isranascimento.hotelslist.models.Hotel

data class HotelUI(
    val name: String
) {
    companion object {
        fun fromDomainModel(domain: Hotel) = HotelUI(
            name = domain.name
        )
    }
}