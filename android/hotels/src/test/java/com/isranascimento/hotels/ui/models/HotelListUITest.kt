package com.isranascimento.hotels.ui.models

import com.google.common.truth.Truth.assertThat
import com.isranascimento.hotels.util.ReturnedValues.HOTEL_DOMAIN_LIST
import com.isranascimento.hotels.util.createHotelUIItem
import com.isranascimento.theme.HotelCardItem
import org.junit.Test

class HotelListUITest {

    @Test
    fun `hotelUI map function returns expected value`() {
        val hotelUIModelList = HotelListUI.fromHotelListDomainClass(
            HOTEL_DOMAIN_LIST
        )

        assertThat(hotelUIModelList.count()).isEqualTo(7)

        assertTitle(hotelUIModelList[0], HotelListUITitle(4f))
        assertHotelItem(hotelUIModelList[1], createHotelUIItem(4))

        assertTitle(hotelUIModelList[2], HotelListUITitle(3f))
        assertHotelItem(hotelUIModelList[3], createHotelUIItem(1))
        assertHotelItem(hotelUIModelList[4], createHotelUIItem(2))

        assertTitle(hotelUIModelList[5], HotelListUITitle(1f))
        assertHotelItem(hotelUIModelList[6], createHotelUIItem(3))

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