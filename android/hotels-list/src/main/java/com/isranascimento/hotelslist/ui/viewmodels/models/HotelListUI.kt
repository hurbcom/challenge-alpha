package com.isranascimento.hotelslist.ui.viewmodels.models

import com.isranascimento.hotelslist.models.Hotel

data class HotelListUI(
    val starCount: Int,
    val hotels: MutableList<HotelUI>
) {
    companion object {
        fun fromHotelListDomainClass(domain: List<Hotel>): List<HotelListUI> {
            val hotelList = mutableListOf<HotelListUI>()
            domain.forEach { hotelDomain ->
                val currentHotelList = hotelList.find { hotelListUI ->
                    hotelListUI.starCount == hotelDomain.starCount
                }
                if(currentHotelList != null) {
                    currentHotelList.hotels.add(HotelUI.fromDomainModel(hotelDomain))
                } else {
                    val newHotelListUI = HotelListUI(
                        starCount = hotelDomain.starCount,
                        hotels = mutableListOf(HotelUI.fromDomainModel(hotelDomain))
                    )
                    hotelList.add(newHotelListUI)
                }
            }
            return hotelList.sortedBy {
                it.starCount
            }
        }
    }
}
