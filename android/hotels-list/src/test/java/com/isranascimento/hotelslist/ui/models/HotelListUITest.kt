package com.isranascimento.hotelslist.ui.models

import com.google.common.truth.Truth.assertThat
import com.isranascimento.hotelslist.models.Address
import com.isranascimento.hotelslist.models.Hotel
import com.isranascimento.hotelslist.ui.viewmodels.models.HotelListUI
import com.isranascimento.hotelslist.ui.viewmodels.models.HotelListUIItem
import com.isranascimento.hotelslist.util.ReturnedValues.HOTEL_DOMAIN_LIST
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
        assertThat(firstItem.hotelLists.count()).isEqualTo(1)
        assertThat(firstItem.hotelLists[0]).isEqualTo(HotelListUIItem("Hotel 3"))

        val secondItem = hotelUIModelList[1]
        assertThat(secondItem.starCount).isEqualTo(3)
        assertThat(secondItem.hotelLists.size).isEqualTo(2)
        assertThat(secondItem.hotelLists[0].name).isEqualTo("Hotel 1")
        assertThat(secondItem.hotelLists[1].name).isEqualTo("Hotel 2")
    }
}