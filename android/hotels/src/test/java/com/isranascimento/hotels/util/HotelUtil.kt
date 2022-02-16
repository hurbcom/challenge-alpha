package com.isranascimento.hotels.util

import com.isranascimento.hotels.ui.models.HotelListUIItem
import com.isranascimento.theme.hotel.HotelCardItem

fun createHotelUIItem(number: Int) = HotelListUIItem(
    HotelCardItem(
    id = number.toString(),
    name = "Hotel $number",
    image = "Image $number",
    city = "City $number",
    state = "State $number",
    amenities = listOf("Amenity $number", "Amenity $number", "Amenity $number")
)
)