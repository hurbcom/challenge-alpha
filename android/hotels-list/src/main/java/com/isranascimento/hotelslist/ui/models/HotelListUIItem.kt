package com.isranascimento.hotelslist.ui.models

import com.isranascimento.hotelslist.models.Hotel

data class HotelListUIItem(
    val sku: String,
    val name: String
): HotelListUI() {
    companion object {
        fun fromDomainModel(domain: Hotel) = HotelListUIItem(
            name = domain.name,
            sku = domain.sku
        )
    }
}