package com.filipeoliveira.hurbchallenge.data

import com.filipeoliveira.hurbchallenge.ui.model.HotelInfoUI

interface HotelRepository {
    fun getHotelList(query: String, enabledFilters: List<String>): HotelInfoUI
}