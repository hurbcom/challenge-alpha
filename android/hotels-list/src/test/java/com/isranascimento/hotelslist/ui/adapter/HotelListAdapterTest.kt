package com.isranascimento.hotelslist.ui.adapter

import android.widget.FrameLayout
import androidx.test.runner.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.isranascimento.androidtestutils.TestContextProvider.context
import com.isranascimento.hotelslist.ui.adapter.HotelsListViewType.CARD
import com.isranascimento.hotelslist.ui.adapter.HotelsListViewType.TITLE
import com.isranascimento.hotelslist.ui.adapter.holder.BaseHotelListHolder
import com.isranascimento.hotelslist.ui.adapter.holder.HotelListCardHolder
import com.isranascimento.hotelslist.ui.adapter.holder.HotelListTitleHolder
import com.isranascimento.hotelslist.ui.models.HotelListUIItem
import com.isranascimento.hotelslist.ui.models.HotelListUITitle
import com.isranascimento.hotelslist.util.createHotelUIItem
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HotelListAdapterTest {
    private lateinit var sut: HotelsListAdapter
    private lateinit var viewGroup: FrameLayout

    @Before
    fun setup() {
        sut = HotelsListAdapter()
        viewGroup = FrameLayout(context())
    }

    @Test
    fun `WHEN creating a view holder with type card THEN HotelListCardHolder is returned`() {
        val holder = sut.onCreateViewHolder(viewGroup, CARD)
        assertThat(holder).isInstanceOf(HotelListCardHolder::class.java)
    }

    @Test
    fun `WHEN creating a view holder with type title THEN HotelListTitleHolder is returned`() {
        val holder = sut.onCreateViewHolder(viewGroup, TITLE)
        assertThat(holder).isInstanceOf(HotelListTitleHolder::class.java)
    }

    @Test
    fun `WHEN binding a view holder THEN BaseHotelListHolder bind is called`() {
        val viewHolderMockk = mockk<BaseHotelListHolder>()
        every { viewHolderMockk.bind(any()) } just Runs

        sut.submitList(listOf(createHotelUIItem(1)))
        sut.onBindViewHolder(viewHolderMockk, 0)

        verify {
            viewHolderMockk.bind(createHotelUIItem(1))
        }
    }

    @Test
    fun `WHEN getItemViewType with position on HotelListUITitle THEN HotelListViewType_TITLE is returned`() {
        sut.submitList(listOf(HotelListUITitle(3f)))
        val itemViewType = sut.getItemViewType(0)

        assertThat(itemViewType).isEqualTo(TITLE)
    }

    @Test
    fun `WHEN getItemViewType with position on HotelListUICard THEN HotelListViewType_CARD is returned`() {
        sut.submitList(listOf(createHotelUIItem(1)))
        val itemViewType = sut.getItemViewType(0)

        assertThat(itemViewType).isEqualTo(CARD)
    }
}