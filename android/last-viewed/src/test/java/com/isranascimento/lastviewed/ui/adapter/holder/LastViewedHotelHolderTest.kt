package com.isranascimento.lastviewed.ui.adapter.holder

import com.isranascimento.theme.hotel.HotelCardItem
import com.isranascimento.theme.hotel.HotelCardItemView
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class LastViewedHotelHolderTest {
    private lateinit var sut: LastViewedHotelHolder
    private val cardViewMock: HotelCardItemView = mockk()
    private val listener = object: HotelCardItemView.HotelCardItemListener {
        override fun onHotelClick(hotelId: String) = Unit
    }

    @Before
    fun setup() {
        every { cardViewMock.bind(any()) } just Runs
        sut = LastViewedHotelHolder(cardViewMock)
    }

    @Test
    fun `WHEN the holder bind is called THEN it calls the card bind with correct argument`() {
        sut.bind(createHotelCardItem())

        verify {
            cardViewMock.bind(createHotelCardItem())
        }
    }

    @Test
    fun `WHEN the holder bindListener is called THEN it calls the card bind listener correctly`() {
        every { cardViewMock.bindListener(any(), any()) } just Runs

        sut.bindListener(createHotelCardItem(), listener)

        verify {
            cardViewMock.bindListener(createHotelCardItem(), listener)
        }

    }

    private fun createHotelCardItem(): HotelCardItem = HotelCardItem(
        id = "1",
        name = "name",
        image = "image",
        city = "city",
        state = "state",
        amenities = listOf("amenities")
    )
}