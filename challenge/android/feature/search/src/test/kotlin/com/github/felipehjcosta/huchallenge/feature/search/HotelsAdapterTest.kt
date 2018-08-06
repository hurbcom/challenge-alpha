package com.github.felipehjcosta.huchallenge.feature.search

import android.widget.FrameLayout
import com.github.felipehjcosta.huchallenge.feature.TestStubApplication
import com.github.felipehjcosta.huchallenge.feature.search.viewmodel.HotelListItemViewModel
import com.github.felipehjcosta.huchallenge.feature.search.viewmodel.HotelSectionListItemViewModel
import com.github.felipehjcosta.huchallenge.feature.search.viewmodel.ListViewModel
import com.github.felipehjcosta.huchallenge.feature.search.viewmodel.PackageSectionListItemViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals
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
        assertEquals(1, hotelsAdapter.itemCount)
    }

    @Test
    fun ensureSectionTypeWhenCallListViewModel() {
        every { mockListViewModel.isSection(0) } returns true
        assertEquals(HotelsAdapter.HOTEL_SECTION_VIEW_TYPE, hotelsAdapter.getItemViewType(0))
    }

    @Test
    fun ensureHotelTypeWhenCallListViewModel() {
        every { mockListViewModel.isSection(1) } returns false
        assertEquals(hotelsAdapter.getItemViewType(1), HotelsAdapter.HOTEL_VIEW_TYPE)
    }

    @Test
    fun ensurePackageSectionTypeWhenCallListViewModel() {
        every { mockListViewModel.isPackageSection(0) } returns true
        assertEquals(HotelsAdapter.PACKAGE_SECTION_VIEW_TYPE, hotelsAdapter.getItemViewType(0))
    }

    @Test
    fun ensurePackageTypeWhenCallListViewModel() {
        every { mockListViewModel.isPackage(1) } returns true
        assertEquals(HotelsAdapter.PACKAGE_VIEW_TYPE, hotelsAdapter.getItemViewType(1))
    }

    @Test
    fun ensureSectionBindingWhenCallListViewModel() {
        val context = Robolectric.buildActivity(SearchActivity::class.java).create().get()
        val parent = FrameLayout(context)
        val holder = hotelsAdapter.createViewHolder(parent, HotelsAdapter.HOTEL_SECTION_VIEW_TYPE)

        val listItemViewModel = mockk<HotelSectionListItemViewModel>()
        val title = "section"
        every { listItemViewModel.name } returns title
        every { mockListViewModel[0] } returns listItemViewModel
        hotelsAdapter.bindViewHolder(holder, 0)

        val currentTitle = (holder as HotelsAdapter.HotelSectionViewHolder).title.text
        assertEquals(title, currentTitle)
    }

    @Test
    fun ensureHotelBindingWhenCallListViewModel() {
        val context = Robolectric.buildActivity(SearchActivity::class.java).create().get()
        val parent = FrameLayout(context)
        val holder = hotelsAdapter.createViewHolder(parent, HotelsAdapter.HOTEL_VIEW_TYPE)

        val listItemViewModel = mockk<HotelListItemViewModel>()
        val title = "hotel"
        every { listItemViewModel.name } returns title
        every { mockListViewModel[1] } returns listItemViewModel
        hotelsAdapter.bindViewHolder(holder, 1)

        assertTrue { (holder as HotelsAdapter.HotelsViewHolder).title.text == title }
    }

    @Test
    fun ensurePackageSectionBindingWhenCallListViewModel() {
        val context = Robolectric.buildActivity(SearchActivity::class.java).create().get()
        val parent = FrameLayout(context)
        val holder = hotelsAdapter.createViewHolder(parent, HotelsAdapter.PACKAGE_SECTION_VIEW_TYPE)

        val listItemViewModel = mockk<PackageSectionListItemViewModel>()
        val title = null
        every { listItemViewModel.name } returns title
        every { mockListViewModel[0] } returns listItemViewModel
        hotelsAdapter.bindViewHolder(holder, 0)

        verify(exactly = 0) { listItemViewModel.name }
    }

    @Test
    fun ensurePackageBindingWhenCallListViewModel() {
        val context = Robolectric.buildActivity(SearchActivity::class.java).create().get()
        val parent = FrameLayout(context)
        val holder = hotelsAdapter.createViewHolder(parent, HotelsAdapter.PACKAGE_VIEW_TYPE)

        val listItemViewModel = mockk<HotelListItemViewModel>()
        val title = "package"
        every { listItemViewModel.name } returns title
        every { mockListViewModel[1] } returns listItemViewModel
        hotelsAdapter.bindViewHolder(holder, 1)

        val currentTitle = (holder as HotelsAdapter.PackageViewHolder).title.text
        assertEquals(title, currentTitle)
    }
}