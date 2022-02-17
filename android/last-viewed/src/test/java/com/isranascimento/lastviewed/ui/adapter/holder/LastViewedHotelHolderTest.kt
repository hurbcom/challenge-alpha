package com.isranascimento.lastviewed.ui.adapter.holder

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.isranascimento.lastviewed.ui.adapter.holder.LastViewedHotelHolder.Companion.CARD_WIDTH_IN_PERCENTAGE
import com.isranascimento.theme.hotel.HotelCardItem
import com.isranascimento.theme.hotel.HotelCardItemView
import com.isranascimento.utils.extensions.screenWidthInPx
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LastViewedHotelHolderTest {
    private lateinit var sut: LastViewedHotelHolder
    private val cardViewMock: HotelCardItemView = mockk()
    private val listener = object: HotelCardItemView.HotelCardItemListener {
        override fun onHotelClick(hotelId: String) = Unit
    }
    @Before
    fun setup() {
        every { cardViewMock.bind(any(), any()) } just Runs
        mockkStatic(Context::screenWidthInPx)
        sut = LastViewedHotelHolder(cardViewMock)
    }

    @Test
    fun `WHEN the holder bind is called THEN it calls the card bind with correct argument`() {
        every { sut.itemView.context.screenWidthInPx() } returns 100

        sut.bind(createHotelCardItem())

        verify {
            cardViewMock.bind(createHotelCardItem(), (100 * CARD_WIDTH_IN_PERCENTAGE).toInt())
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