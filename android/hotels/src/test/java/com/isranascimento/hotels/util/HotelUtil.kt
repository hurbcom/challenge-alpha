package com.isranascimento.hotels.util

import com.isranascimento.hotels.ui.models.HotelListUIItem

fun createHotelUIItem(number: Int) = HotelListUIItem(
    sku = number.toString(),
    name = "Hotel $number",
    image = "Image $number",
    city = "City $number",
    state = "State $number",
    amenities = listOf("Amenity $number", "Amenity $number", "Amenity $number")
)