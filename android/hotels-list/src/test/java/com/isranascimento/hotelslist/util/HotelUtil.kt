package com.isranascimento.hotelslist.util

import com.isranascimento.hotelslist.models.Hotel
import com.isranascimento.hotelslist.ui.models.HotelListUIItem

fun createExpectedHotelUIItem(number: Int) = HotelListUIItem(
    number.toString(),
    "Hotel $number"
)