package com.isranascimento.hotelslist.ui.models

import com.google.common.truth.Truth.assertThat
import com.isranascimento.hotelslist.models.Address
import com.isranascimento.hotelslist.models.Hotel
import com.isranascimento.hotelslist.ui.viewmodels.models.HotelListUI
import com.isranascimento.hotelslist.ui.viewmodels.models.HotelUI
import org.junit.Test

class HotelListUITest {

    @Test
    fun `hotelUI map function returns expected value`() {
        val hotelUIModelList = HotelListUI.fromHotelListDomainClass(
            HOTEL_DOMAIN_LIST
        )

        assertThat(hotelUIModelList.count()).isEqualTo(2)

        val firstItem = hotelUIModelList[0]
        assertThat(firstItem.starCount).isEqualTo(1)
        assertThat(firstItem.hotels.count()).isEqualTo(1)
        assertThat(firstItem.hotels[0]).isEqualTo(HotelUI("Hotel 3"))

        val secondItem = hotelUIModelList[1]
        assertThat(secondItem.starCount).isEqualTo(3)
        assertThat(secondItem.hotels.size).isEqualTo(2)
        assertThat(secondItem.hotels[0].name).isEqualTo("Hotel 1")
        assertThat(secondItem.hotels[1].name).isEqualTo("Hotel 2")
    }

    companion object {
        private val HOTEL_DOMAIN_LIST = listOf<Hotel>(
            Hotel(
                id = "1",
                sku = "2",
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
            )
        )
    }
}