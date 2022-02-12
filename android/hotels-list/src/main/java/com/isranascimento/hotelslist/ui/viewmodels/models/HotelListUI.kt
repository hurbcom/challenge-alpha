package com.isranascimento.hotelslist.ui.viewmodels.models

import com.isranascimento.hotelslist.models.Hotel

data class HotelListUI(
    val starCount: Int,
    val hotelLists: MutableList<HotelListUIItem>
) {
    companion object {
        fun fromHotelListDomainClass(domain: List<Hotel>): List<HotelListUI> {
            val hotelList = mutableListOf<HotelListUI>()
            domain.forEach { hotelDomain ->
                val currentHotelList = hotelList.find { hotelListUI ->
                    hotelListUI.starCount == hotelDomain.starCount
                }
                if(currentHotelList != null) {
                    currentHotelList.hotelLists.add(HotelListUIItem.fromDomainModel(hotelDomain))
                } else {
                    val newHotelListUI = HotelListUI(
                        starCount = hotelDomain.starCount,
                        hotelLists = mutableListOf(HotelListUIItem.fromDomainModel(hotelDomain))
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
