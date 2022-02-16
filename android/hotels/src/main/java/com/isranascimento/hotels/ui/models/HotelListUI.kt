package com.isranascimento.hotels.ui.models

import com.isranascimento.coremodels.hotel.Hotel

sealed class HotelListUI {
    companion object {
        fun fromHotelListDomainClass(domain: List<Hotel>): List<HotelListUI> {
            val hotelList = mutableListOf<HotelListUI>()
            val hotelGroup = domain.groupBy { it.starCount }.toSortedMap(reverseOrder())
            hotelGroup.forEach { (key, value) ->
                hotelList.add(HotelListUITitle(key.toFloat()))
                value.forEach {
                    hotelList.add(HotelListUIItem.fromDomainModel(it))
                }
            }
            return hotelList
        }
    }
}
