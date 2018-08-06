package com.github.felipehjcosta.huchallenge.feature.search

import android.view.View
import android.widget.FrameLayout
import com.github.felipehjcosta.huchallenge.base.imageloader.ImageLoader
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
import java.math.BigDecimal
import java.text.NumberFormat
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(
        application = TestStubApplication::class,
        manifest = Config.NONE,
        constants = BuildConfig::class,
        sdk = [21]
)
class HotelsAdapterTest {

    private val mockListViewModel = mockk<ListViewModel>(relaxed = true)

    private val mockImageLoader = mockk<ImageLoader>(relaxed = true)

    private val hotelsAdapter = HotelsAdapter(mockListViewModel, mockImageLoader)

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
        val title = "3"
        every { listItemViewModel.name } returns title
        every { mockListViewModel[0] } returns listItemViewModel
        hotelsAdapter.bindViewHolder(holder, 0)

        val currentTitle = (holder as HotelsAdapter.HotelSectionViewHolder).title.text
        assertEquals("3 estrelas", currentTitle)
    }

    @Test
    fun ensureHotelBindingWhenCallListViewModel() {
        val context = Robolectric.buildActivity(SearchActivity::class.java).create().get()
        val parent = FrameLayout(context)
        val holder = hotelsAdapter.createViewHolder(parent, HotelsAdapter.HOTEL_VIEW_TYPE)

        val listItemViewModel = mockk<HotelListItemViewModel>(relaxed = true)
        val imageUrl = "https://novo-hu.s3.amazonaws.com/reservas/ota/prod/hotel/2822/hotel-vilamar-copacabana-rio-de-janeiro-rj-001_20180409114156.png"
        val image = imageUrl.apply { every { listItemViewModel.imageUrl } returns this }
        val title = "hotel".apply { every { listItemViewModel.name } returns this }
        val city = "Rio de Janeiro".apply { every { listItemViewModel.city } returns this }
        val state = "Rio de Janeiro".apply { every { listItemViewModel.state } returns this }
        val amount = NumberFormat.getCurrencyInstance().format(BigDecimal("159.99"))
        val price = amount.apply { every { listItemViewModel.price } returns this }
        val amenity1 = "amenity1".apply { every { listItemViewModel.amenity1 } returns this }
        val amenity2 = "amenity1".apply { every { listItemViewModel.amenity2 } returns this }
        val amenity3 = "amenity1".apply { every { listItemViewModel.amenity3 } returns this }
        every { mockListViewModel[1] } returns listItemViewModel
        hotelsAdapter.bindViewHolder(holder, 1)

        assertEquals(title, (holder as HotelsAdapter.HotelsViewHolder).title.text)
        assertEquals("$city - $state", holder.address.text)
        assertEquals(price, holder.price.text)
        assertEquals(View.VISIBLE, holder.amenityTitle.visibility)
        assertEquals(amenity1, holder.amenity1.text)
        assertEquals(amenity2, holder.amenity2.text)
        assertEquals(amenity3, holder.amenity3.text)
        verify { mockImageLoader.loadImage(image, holder.image) }
    }

    @Test
    fun ensureHotelBindingAmenityTitleIsNotShownWhenCallListViewModelWithNoAmenity() {
        val context = Robolectric.buildActivity(SearchActivity::class.java).create().get()
        val parent = FrameLayout(context)
        val holder = hotelsAdapter.createViewHolder(parent, HotelsAdapter.HOTEL_VIEW_TYPE)

        val listItemViewModel = mockk<HotelListItemViewModel>(relaxed = true)
        "".apply { every { listItemViewModel.amenity1 } returns this }
        "".apply { every { listItemViewModel.amenity2 } returns this }
        "".apply { every { listItemViewModel.amenity3 } returns this }
        every { mockListViewModel[1] } returns listItemViewModel
        hotelsAdapter.bindViewHolder(holder, 1)

        assertEquals(View.GONE, (holder as HotelsAdapter.HotelsViewHolder).amenityTitle.visibility)
    }

    @Test
    fun ensureHotelAmenityBindingCorrectlyWhenCallListViewModel() {
        val context = Robolectric.buildActivity(SearchActivity::class.java).create().get()
        val parent = FrameLayout(context)
        val holder = hotelsAdapter.createViewHolder(parent, HotelsAdapter.HOTEL_VIEW_TYPE)

        val listItemViewModelWithoutAmenities = mockk<HotelListItemViewModel>(relaxed = true)
        "".apply { every { listItemViewModelWithoutAmenities.amenity1 } returns this }
        "".apply { every { listItemViewModelWithoutAmenities.amenity2 } returns this }
        "".apply { every { listItemViewModelWithoutAmenities.amenity3 } returns this }
        every { mockListViewModel[1] } returns listItemViewModelWithoutAmenities
        hotelsAdapter.bindViewHolder(holder, 1)

        assertEquals(View.GONE, (holder as HotelsAdapter.HotelsViewHolder).amenityTitle.visibility)
        assertEquals(View.GONE, holder.amenity1.visibility)
        assertEquals(View.GONE, holder.amenity2.visibility)
        assertEquals(View.GONE, holder.amenity3.visibility)

        val listItemViewModelWithAmenities = mockk<HotelListItemViewModel>(relaxed = true)
        "amenity1".apply { every { listItemViewModelWithAmenities.amenity1 } returns this }
        "amenity2".apply { every { listItemViewModelWithAmenities.amenity2 } returns this }
        "amenity3".apply { every { listItemViewModelWithAmenities.amenity3 } returns this }
        every { mockListViewModel[2] } returns listItemViewModelWithAmenities
        hotelsAdapter.bindViewHolder(holder, 2)

        assertEquals(View.VISIBLE, (holder as HotelsAdapter.HotelsViewHolder).amenityTitle.visibility)
        assertEquals(View.VISIBLE, holder.amenity1.visibility)
        assertEquals(View.VISIBLE, holder.amenity2.visibility)
        assertEquals(View.VISIBLE, holder.amenity3.visibility)
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

        val listItemViewModel = mockk<HotelListItemViewModel>(relaxed = true)
        val imageUrl = "https://novo-hu.s3.amazonaws.com/reservas/ota/prod/hotel/2822/hotel-vilamar-copacabana-rio-de-janeiro-rj-001_20180409114156.png"
        val image = imageUrl.apply { every { listItemViewModel.imageUrl } returns this }
        val title = "package".apply { every { listItemViewModel.name } returns this }
        val city = "Rio de Janeiro".apply { every { listItemViewModel.city } returns this }
        val state = "Rio de Janeiro".apply { every { listItemViewModel.state } returns this }
        val amount = NumberFormat.getCurrencyInstance().format(BigDecimal("159.99"))
        val price = amount.apply { every { listItemViewModel.price } returns this }
        val amenity1 = "amenity1".apply { every { listItemViewModel.amenity1 } returns this }
        val amenity2 = "amenity1".apply { every { listItemViewModel.amenity2 } returns this }
        val amenity3 = "amenity1".apply { every { listItemViewModel.amenity3 } returns this }
        every { mockListViewModel[1] } returns listItemViewModel
        hotelsAdapter.bindViewHolder(holder, 1)

        assertEquals(title, (holder as HotelsAdapter.HotelsViewHolder).title.text)
        assertEquals("$city - $state", holder.address.text)
        assertEquals(price, holder.price.text)
        assertEquals(View.VISIBLE, holder.amenityTitle.visibility)
        assertEquals(amenity1, holder.amenity1.text)
        assertEquals(amenity2, holder.amenity2.text)
        assertEquals(amenity3, holder.amenity3.text)
        verify { mockImageLoader.loadImage(image, holder.image) }
    }

    @Test
    fun ensurePackageBindingAmenityTitleIsNotShownWhenCallListViewModelWithNoAmenity() {
        val context = Robolectric.buildActivity(SearchActivity::class.java).create().get()
        val parent = FrameLayout(context)
        val holder = hotelsAdapter.createViewHolder(parent, HotelsAdapter.HOTEL_VIEW_TYPE)

        val listItemViewModel = mockk<HotelListItemViewModel>(relaxed = true)
        "".apply { every { listItemViewModel.amenity1 } returns this }
        "".apply { every { listItemViewModel.amenity2 } returns this }
        "".apply { every { listItemViewModel.amenity3 } returns this }
        every { mockListViewModel[1] } returns listItemViewModel
        hotelsAdapter.bindViewHolder(holder, 1)

        assertEquals(View.GONE, (holder as HotelsAdapter.HotelsViewHolder).amenityTitle.visibility)
    }

    @Test
    fun ensurePackageAmenityBindingCorrectlyWhenCallListViewModel() {
        val context = Robolectric.buildActivity(SearchActivity::class.java).create().get()
        val parent = FrameLayout(context)
        val holder = hotelsAdapter.createViewHolder(parent, HotelsAdapter.HOTEL_VIEW_TYPE)

        val listItemViewModelWithoutAmenities = mockk<HotelListItemViewModel>(relaxed = true)
        "".apply { every { listItemViewModelWithoutAmenities.amenity1 } returns this }
        "".apply { every { listItemViewModelWithoutAmenities.amenity2 } returns this }
        "".apply { every { listItemViewModelWithoutAmenities.amenity3 } returns this }
        every { mockListViewModel[1] } returns listItemViewModelWithoutAmenities
        hotelsAdapter.bindViewHolder(holder, 1)

        assertEquals(View.GONE, (holder as HotelsAdapter.HotelsViewHolder).amenityTitle.visibility)
        assertEquals(View.GONE, holder.amenity1.visibility)
        assertEquals(View.GONE, holder.amenity2.visibility)
        assertEquals(View.GONE, holder.amenity3.visibility)

        val listItemViewModelWithAmenities = mockk<HotelListItemViewModel>(relaxed = true)
        "amenity1".apply { every { listItemViewModelWithAmenities.amenity1 } returns this }
        "amenity2".apply { every { listItemViewModelWithAmenities.amenity2 } returns this }
        "amenity3".apply { every { listItemViewModelWithAmenities.amenity3 } returns this }
        every { mockListViewModel[2] } returns listItemViewModelWithAmenities
        hotelsAdapter.bindViewHolder(holder, 2)

        assertEquals(View.VISIBLE, (holder as HotelsAdapter.HotelsViewHolder).amenityTitle.visibility)
        assertEquals(View.VISIBLE, holder.amenity1.visibility)
        assertEquals(View.VISIBLE, holder.amenity2.visibility)
        assertEquals(View.VISIBLE, holder.amenity3.visibility)
    }
}