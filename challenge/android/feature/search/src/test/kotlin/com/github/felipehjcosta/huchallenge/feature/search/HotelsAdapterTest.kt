package com.github.felipehjcosta.huchallenge.feature.search

import android.widget.FrameLayout
import com.github.felipehjcosta.huchallenge.feature.TestStubApplication
import com.github.felipehjcosta.huchallenge.feature.search.viewmodel.HotelListItemViewModel
import com.github.felipehjcosta.huchallenge.feature.search.viewmodel.ListViewModel
import com.github.felipehjcosta.huchallenge.feature.search.viewmodel.SectionListItemViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
@Config(
        application = TestStubApplication::class,
        manifest = Config.NONE,
        constants = BuildConfig::class,
        sdk = [21]
)
class HotelsAdapterTest {

    private val mockListViewModel = mockk<ListViewModel>(relaxed = true)

    private val hotelsAdapter = HotelsAdapter(mockListViewModel)

    @Test
    fun ensureSizeWhenCallListViewModel() {
        every { mockListViewModel.size } returns 1
        assertTrue { hotelsAdapter.itemCount == 1 }
    }

    @Test
    fun ensureSectionTypeWhenCallListViewModel() {
        every { mockListViewModel.isSection(0) } returns true
        assertTrue { hotelsAdapter.getItemViewType(0) == HotelsAdapter.SECTION_VIEW_TYPE }
    }

    @Test
    fun ensureHotelTypeWhenCallListViewModel() {
        every { mockListViewModel.isSection(1) } returns false
        assertTrue { hotelsAdapter.getItemViewType(1) == HotelsAdapter.HOTEL_VIEW_TYPE }
    }

    @Test
    fun ensureSectionHolderCreationWhenCallListViewModel() {
        val context = Robolectric.buildActivity(SearchActivity::class.java).create().get()
        val parent = FrameLayout(context)
        assertTrue { hotelsAdapter.createViewHolder(parent, HotelsAdapter.SECTION_VIEW_TYPE).javaClass == HotelsAdapter.SectionViewHolder::class.java }
    }

    @Test
    fun ensureHotelHolderCreationWhenCallListViewModel() {
        val context = Robolectric.buildActivity(SearchActivity::class.java).create().get()
        val parent = FrameLayout(context)
        assertTrue { hotelsAdapter.createViewHolder(parent, HotelsAdapter.SECTION_VIEW_TYPE).javaClass == HotelsAdapter.SectionViewHolder::class.java }
    }

    @Test
    fun ensureSectionBindingWhenCallListViewModel() {
        val context = Robolectric.buildActivity(SearchActivity::class.java).create().get()
        val parent = FrameLayout(context)
        val sectionHolder = hotelsAdapter.createViewHolder(parent, HotelsAdapter.SECTION_VIEW_TYPE)

        val sectionListItemViewModel = mockk<SectionListItemViewModel>()
        val sectionTitle = "section"
        every { sectionListItemViewModel.name } returns sectionTitle
        every { mockListViewModel[0] } returns sectionListItemViewModel
        hotelsAdapter.bindViewHolder(sectionHolder, 0)

        assertTrue { (sectionHolder as HotelsAdapter.SectionViewHolder).title.text == sectionTitle }
    }

    @Test
    fun ensureHotelBindingWhenCallListViewModel() {
        val context = Robolectric.buildActivity(SearchActivity::class.java).create().get()
        val parent = FrameLayout(context)
        val sectionHolder = hotelsAdapter.createViewHolder(parent, HotelsAdapter.HOTEL_VIEW_TYPE)

        val hotelListItemViewModel = mockk<HotelListItemViewModel>()
        val sectionTitle = "hotel"
        every { hotelListItemViewModel.name } returns sectionTitle
        every { mockListViewModel[1] } returns hotelListItemViewModel
        hotelsAdapter.bindViewHolder(sectionHolder, 1)

        assertTrue { (sectionHolder as HotelsAdapter.HotelsViewHolder).title.text == sectionTitle }
    }
}