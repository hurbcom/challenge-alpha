package com.isranascimento.hotelslist.util

import com.isranascimento.hotelslist.models.Hotel
import com.isranascimento.hotelslist.ui.models.HotelListUIItem

fun createHotelUIItem(number: Int) = HotelListUIItem(
    sku = number.toString(),
    name = "Hotel $number",
    image = "Image $number",
    city = "City $number",
    state = "State $number",
    amenities = listOf("Amenity $number")
)