package com.isranascimento.hotelslist.ui.models

import com.isranascimento.hotelslist.models.Hotel

sealed class HotelListUI {
    companion object {
        fun fromHotelListDomainClass(domain: List<Hotel>): List<HotelListUI> {
            val hotelList = mutableListOf<HotelListUI>()
            val hotelGroup = domain.groupBy { it.starCount }.toSortedMap(reverseOrder())
            hotelGroup.forEach { (key, value) ->
                hotelList.add(HotelListUITitle(key))
                value.forEach {
                    hotelList.add(HotelListUIItem.fromDomainModel(it))
                }
            }
            return hotelList
        }
    }
}
