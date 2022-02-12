package com.isranascimento.hotelslist.ui.models

import com.google.common.truth.Truth.assertThat
import com.isranascimento.hotelslist.models.Address
import com.isranascimento.hotelslist.models.Hotel
import com.isranascimento.hotelslist.ui.viewmodels.models.HotelListUI
import com.isranascimento.hotelslist.ui.viewmodels.models.HotelListUIItem
import com.isranascimento.hotelslist.ui.viewmodels.models.HotelListUITitle
import com.isranascimento.hotelslist.util.ReturnedValues.HOTEL_DOMAIN_LIST
import org.junit.Test

class HotelListUITest {

    @Test
    fun `hotelUI map function returns expected value`() {
        val hotelUIModelList = HotelListUI.fromHotelListDomainClass(
            HOTEL_DOMAIN_LIST
        )

        assertThat(hotelUIModelList.count()).isEqualTo(7)

        assertTitle(hotelUIModelList[0], HotelListUITitle(4))
        assertHotelItem(hotelUIModelList[1], HotelListUIItem("Hotel 4"))

        assertTitle(hotelUIModelList[2], HotelListUITitle(3))
        assertHotelItem(hotelUIModelList[3], HotelListUIItem("Hotel 1"))
        assertHotelItem(hotelUIModelList[4], HotelListUIItem("Hotel 2"))

        assertTitle(hotelUIModelList[5], HotelListUITitle(1))
        assertHotelItem(hotelUIModelList[6], HotelListUIItem("Hotel 3"))

    }

    private fun assertTitle(item: HotelListUI, expected: HotelListUITitle) {
        assertThat(item).isInstanceOf(HotelListUITitle::class.java)
        assertThat(item).isEqualTo(expected)
    }

    private fun assertHotelItem(item: HotelListUI, expected: HotelListUIItem) {
        assertThat(item).isInstanceOf(HotelListUIItem::class.java)
        assertThat(item).isEqualTo(expected)
    }
}