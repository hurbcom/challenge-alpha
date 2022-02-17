package com.isranascimento.hoteldetail.util

import com.isranascimento.coremodels.hotel.Address
import com.isranascimento.coremodels.hotel.Hotel

fun createHotel() = Hotel(
    id = "1",
    name = "Hotel 1",
    gallery = listOf("item1"),
    amenities = listOf("Amenity 1", "Amenity 1", "Amenity 1", "Amenity 1", "Amenity 1", "Amenity 1"),
    address = Address(
        "State 1",
        "City 1"
    ),
    starCount = 3,
    mainImage = "Image 1",
    description = "Description 1",
    url = "Share 1"
)