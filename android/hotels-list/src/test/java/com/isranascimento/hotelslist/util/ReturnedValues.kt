package com.isranascimento.hotelslist.util

import com.isranascimento.hotelslist.models.Address
import com.isranascimento.hotelslist.models.Hotel

object ReturnedValues {
    val HOTEL_DOMAIN_LIST = listOf<Hotel>(
        Hotel(
            id = "1",
            sku = "1",
            name = "Hotel 1",
            gallery = listOf("item1"),
            amenities = listOf("amenity1"),
            price = 2.0,
            address = Address(
                "RJ",
                "Rio de Janeiro"
            ),
            starCount = 3
        ),
        Hotel(
            id = "2",
            sku = "2",
            name = "Hotel 2",
            gallery = listOf("item1"),
            amenities = listOf("amenity1"),
            price = 2.0,
            address = Address(
                "RJ",
                "Rio de Janeiro"
            ),
            starCount = 3
        ),
        Hotel(
            id = "3",
            sku = "3",
            name = "Hotel 3",
            gallery = listOf("item1"),
            amenities = listOf("amenity1"),
            price = 2.0,
            address = Address(
                "RJ",
                "Rio de Janeiro"
            ),
            starCount = 1
        ),
        Hotel(
            id = "4",
            sku = "4",
            name = "Hotel 4",
            gallery = listOf("item1"),
            amenities = listOf("amenity1"),
            price = 2.0,
            address = Address(
                "RJ",
                "Rio de Janeiro"
            ),
            starCount = 4
        )
    )
}