package com.isranascimento.hotelslist.ui.models

import com.isranascimento.hotelslist.models.Hotel

data class HotelListUITitle(
    val starCount: Int
): HotelListUI() {
    companion object {
        fun fromDomainModel(domain: Hotel) = HotelListUITitle(
            starCount = domain.starCount
        )
    }
}