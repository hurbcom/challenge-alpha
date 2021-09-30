package com.filipeoliveira.hurbchallenge.ui.model

import java.io.Serializable

data class HotelInfoUI(
    val filters: FilterUI,
    val hotelList: List<HotelUI>
) : Serializable {

}
