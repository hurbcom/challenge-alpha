package com.isranascimento.hotels.ui.models

import com.google.common.truth.Truth.assertThat
import com.isranascimento.hotels.util.ReturnedValues
import org.junit.Test

class HotelDetailUITest {

    @Test
    fun `WHEN fromDomainModels is called THEN it returns correct HotelDetailUI`() {
        val hotel = ReturnedValues.HOTEL_DOMAIN_LIST[0]

        val sut = HotelDetailUI.fromDomainModel(hotel)

        assertThat(sut.amenities.size).isEqualTo(6)
        assertThat(sut.city).isEqualTo("City 1")
        assertThat(sut.state).isEqualTo("State 1")
        assertThat(sut.starCount).isEqualTo(3f)
        assertThat(sut.gallery.size).isEqualTo(1)
        assertThat(sut.description).isEqualTo("Description 1")
        assertThat(sut.shareLink).isEqualTo("Share 1")
        assertThat(sut.name).isEqualTo("Hotel 1")
        assertThat(sut.sku).isEqualTo("1")
    }
}