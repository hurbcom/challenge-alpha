package com.isranascimento.lastviewed.ui.adapter

import android.widget.FrameLayout
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.isranascimento.androidtestutils.TestContextProvider.context
import com.isranascimento.lastviewed.ui.adapter.holder.LastViewedHotelHolder
import com.isranascimento.theme.hotel.HotelCardItem
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LastViewedHotelListAdapterTest {

    private lateinit var sut: LastViewedHotelListAdapter
    private lateinit var viewGroup: FrameLayout

    private lateinit var contract: LastViewedHotelListAdapter.LastViewedHotelListAdapterContract

    @Before
    fun setup() {
        contract = mockk()
        every { contract.onHotelClick(any()) } just Runs

        sut = LastViewedHotelListAdapter(contract)
        viewGroup = FrameLayout(context())
    }

    @Test
    fun `WHEN creating a view holder THEN LastViewedHotelHolder is returned`() {
        val holder = sut.onCreateViewHolder(viewGroup, 0)
        assertThat(holder).isInstanceOf(LastViewedHotelHolder::class.java)
    }

    @Test
    fun `WHEN binding a view holder THEN LastViewedHotelHolder bind is called`() {
        val viewHolderMockk = mockk<LastViewedHotelHolder>()
        every { viewHolderMockk.bind(any()) } just Runs
        every { viewHolderMockk.bindListener(any(), any()) } just Runs

        sut.submitList(listOf(createHotelCardItemModel()))
        sut.onBindViewHolder(viewHolderMockk, 0)

        verify {
            viewHolderMockk.bind(createHotelCardItemModel())
        }
    }

    @Test
    fun `WHEN binding a card THEN the callback is passed to it`() {
        val viewHolderMockk = mockk<LastViewedHotelHolder>()
        every { viewHolderMockk.bind(any()) } just Runs
        every { viewHolderMockk.bindListener(any(), any()) } just Runs

        sut.submitList(listOf(createHotelCardItemModel()))
        sut.onBindViewHolder(viewHolderMockk, 0)

        verify {
            viewHolderMockk.bindListener(createHotelCardItemModel(), contract)
        }
    }

    private fun createHotelCardItemModel() = HotelCardItem(
        id = "1",
        name = "name",
        image = "image",
        city = "city",
        state = "state",
        amenities = listOf("amenities")
    )
}