package com.isranascimento.hotels.util

import com.isranascimento.hotels.models.Address
import com.isranascimento.hotels.models.Hotel

object ReturnedValues {
    val HOTEL_DOMAIN_LIST = listOf<Hotel>(
        Hotel(
            id = "1",
            sku = "1",
            name = "Hotel 1",
            gallery = listOf("item1"),
            amenities = listOf("Amenity 1", "Amenity 1", "Amenity 1", "Amenity 1", "Amenity 1", "Amenity 1"),
            price = 2.0,
            address = Address(
                "State 1",
                "City 1"
            ),
            starCount = 3,
            mainImage = "Image 1",
            description = "Description 1",
            url = "Share 1"
        ),
        Hotel(
            id = "2",
            sku = "2",
            name = "Hotel 2",
            gallery = listOf("item1"),
            amenities = listOf("Amenity 2", "Amenity 2", "Amenity 2", "Amenity 2"),
            price = 2.0,
            address = Address(
                "State 2",
                "City 2"
            ),
            starCount = 3,
            mainImage = "Image 2",
            description = "Description 2",
            url = "Share 2"
        ),
        Hotel(
            id = "3",
            sku = "3",
            name = "Hotel 3",
            gallery = listOf("item1"),
            amenities = listOf("Amenity 3", "Amenity 3", "Amenity 3", "Amenity 3", "Amenity 3", "Amenity 3"),
            price = 2.0,
            address = Address(
                "State 3",
                "City 3"
            ),
            starCount = 1,
            mainImage = "Image 3",
            description = "Description 3",
            url = "Share 3"
        ),
        Hotel(
            id = "4",
            sku = "4",
            name = "Hotel 4",
            gallery = listOf("item1"),
            amenities = listOf("Amenity 4", "Amenity 4", "Amenity 4", "Amenity 4", "Amenity 4", "Amenity 4"),
            price = 2.0,
            address = Address(
                "State 4",
                "City 4"
            ),
            starCount = 4,
            mainImage = "Image 4",
            description = "Description 4",
            url = "Share 4"
        )
    )
}