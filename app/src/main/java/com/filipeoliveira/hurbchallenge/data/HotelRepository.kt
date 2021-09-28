package com.filipeoliveira.hurbchallenge.data

import com.filipeoliveira.hurbchallenge.ui.model.HotelUI

interface HotelRepository {
    fun getHotelList(query: String): List<HotelUI>
}