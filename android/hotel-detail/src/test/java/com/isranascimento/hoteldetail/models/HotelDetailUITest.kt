package com.isranascimento.hoteldetail.models

import com.google.common.truth.Truth.assertThat
import com.isranascimento.coremodels.hotel.Address
import com.isranascimento.coremodels.hotel.Hotel
import com.isranascimento.hoteldetail.util.createHotel
import org.junit.Test

class HotelDetailUITest {

    @Test
    fun `WHEN fromDomainModels is called THEN it returns correct HotelDetailUI`() {
        val hotel = createHotel()

        val sut = HotelDetailUI.fromDomainModel(hotel)

        assertThat(sut.amenities.size).isEqualTo(6)
        assertThat(sut.city).isEqualTo("City 1")
        assertThat(sut.state).isEqualTo("State 1")
        assertThat(sut.starCount).isEqualTo(3f)
        assertThat(sut.gallery.size).isEqualTo(1)
        assertThat(sut.description).isEqualTo("Description 1")
        assertThat(sut.shareLink).isEqualTo("Share 1")
        assertThat(sut.name).isEqualTo("Hotel 1")
        assertThat(sut.id).isEqualTo("1")
    }
}