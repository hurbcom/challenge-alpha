package com.github.felipehjcosta.huchallenge.feature.search.viewmodel

import com.github.felipehjcosta.huchallenge.base.hotels.Hotel
import com.github.felipehjcosta.huchallenge.base.hotels.HotelsRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable.just
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SearchViewModelTest {

    private val mockHotelsRepository = mockk<HotelsRepository>()

    private val viewModel = SearchViewModel(mockHotelsRepository)

    @Before
    fun setUp() {
        RxJavaPlugins.reset()
        RxJavaPlugins.setInitNewThreadSchedulerHandler { Schedulers.trampoline() }

        RxAndroidPlugins.reset()
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun subscribedToShowLoadingWhenExecuteLoadItemsCommandThenReturnItems() {

        val hotelName = "Hotel Vilamar Copacabana"
        val hotel = Hotel(hotelName)
        every { mockHotelsRepository.fetchHotels() } returns just(listOf(hotel))

        val itemsObserver = TestObserver.create<Boolean>()

        viewModel.showLoading.subscribe(itemsObserver)

        val disposable = viewModel.loadItemsCommand.execute().subscribe()

        itemsObserver.assertValues(true, false)

        disposable.dispose()
    }

    @Test
    fun subscribedToItemsWhenExecuteLoadItemsCommandThenReturnItems() {

        val hotelName = "Hotel Vilamar Copacabana"
        val hotel = Hotel(hotelName)
        every { mockHotelsRepository.fetchHotels() } returns just(listOf(hotel))

        val itemsObserver = TestObserver.create<ListViewModel>()

        viewModel.items.subscribe(itemsObserver)

        val disposable = viewModel.loadItemsCommand.execute().subscribe()

        itemsObserver.await(1000L, TimeUnit.MILLISECONDS)

        itemsObserver.assertValueCount(1)

        disposable.dispose()
    }

    @Test
    fun ensureItemsIsEmptyWhenFetchReturnsEmpty() {
        every { mockHotelsRepository.fetchHotels() } returns just(emptyList())

        val itemsObserver = TestObserver.create<ListViewModel>()

        viewModel.items.subscribe(itemsObserver)

        val disposable = viewModel.loadItemsCommand.execute().subscribe()

        itemsObserver.await(1000L, TimeUnit.MILLISECONDS)

        val listViewModel = itemsObserver.values()[0]

        disposable.dispose()

        assertEquals(0, listViewModel.size)
    }

    @Test
    fun ensureItemsContainSectionWhenFetchReturnsHotels() {
        val hotel = Hotel("Hotel Vilamar Copacabana", isHotel = true, stars = 4)

        every { mockHotelsRepository.fetchHotels() } returns just(listOf(hotel))

        val itemsObserver = TestObserver.create<ListViewModel>()

        viewModel.items.subscribe(itemsObserver)

        val disposable = viewModel.loadItemsCommand.execute().subscribe()

        itemsObserver.await(1000L, TimeUnit.MILLISECONDS)

        val listViewModel = itemsObserver.values()[0]

        disposable.dispose()

        assertTrue { listViewModel.isSection(0) }
    }

    @Test
    fun ensureItemsContainHotelsWhenFetchReturnsHotels() {
        val hotel = Hotel(name = "Hotel Vilamar Copacabana", isHotel = true, stars = 4)

        every { mockHotelsRepository.fetchHotels() } returns just(listOf(hotel))

        val itemsObserver = TestObserver.create<ListViewModel>()

        viewModel.items.subscribe(itemsObserver)

        val disposable = viewModel.loadItemsCommand.execute().subscribe()

        itemsObserver.await(1000L, TimeUnit.MILLISECONDS)

        val listViewModel = itemsObserver.values()[0]

        disposable.dispose()

        assertFalse { listViewModel.isSection(1) }
    }

    @Test
    fun ensureItemsIsSectionedWhenFetchReturnsHotels() {
        val hotels = mutableListOf<Hotel>().apply {
            add(Hotel(name = "Hotel Vilamar Copacabana", stars = 3, isHotel = true))
            add(Hotel(name = "Hotel Vilamar Copacabana", stars = 3, isHotel = true))
        }

        every { mockHotelsRepository.fetchHotels() } returns just(hotels)

        val itemsObserver = TestObserver.create<ListViewModel>()

        viewModel.items.subscribe(itemsObserver)

        val disposable = viewModel.loadItemsCommand.execute().subscribe()

        itemsObserver.await(1000L, TimeUnit.MILLISECONDS)

        val listViewModel = itemsObserver.values()[0]

        disposable.dispose()

        assertTrue { listViewModel.size == 3 }
        assertTrue { listViewModel.isSection(0) }
        assertFalse { listViewModel.isSection(1) }
        assertFalse { listViewModel.isSection(2) }
    }

    @Test
    fun ensureItemsIsOrderedInSectionsWhenFetchReturnsHotels() {
        val hotels = mutableListOf<Hotel>().apply {
            add(Hotel(name = "Hotel star 3", stars = 3, isHotel = true))
            add(Hotel(name = "Hotel star 4", stars = 4, isHotel = true))
            add(Hotel(name = "Hotel star 5", stars = 5, isHotel = true))
        }

        every { mockHotelsRepository.fetchHotels() } returns just(hotels)

        val itemsObserver = TestObserver.create<ListViewModel>()

        viewModel.items.subscribe(itemsObserver)

        val disposable = viewModel.loadItemsCommand.execute().subscribe()

        itemsObserver.await(1000L, TimeUnit.MILLISECONDS)

        val listViewModel = itemsObserver.values()[0]

        disposable.dispose()

        assertTrue { listViewModel.size == 6 }
        assertEquals("5", (listViewModel[0] as HotelSectionListItemViewModel).name)
        assertEquals("Hotel star 5", (listViewModel[1] as HotelListItemViewModel).name)
        assertEquals("4", (listViewModel[2] as HotelSectionListItemViewModel).name)
        assertEquals("Hotel star 4", (listViewModel[3] as HotelListItemViewModel).name)
        assertEquals("3", (listViewModel[4] as HotelSectionListItemViewModel).name)
        assertEquals("Hotel star 3", (listViewModel[5] as HotelListItemViewModel).name)
    }

    @Test
    fun ensureItemsContainsSectionedHotelsAndPackagesWhenFetchReturnsHotels() {
        val hotels = mutableListOf<Hotel>().apply {
            add(Hotel(name = "Hotel star 3", stars = 3, isHotel = true))
            add(Hotel(name = "Hotel star 5", stars = 5, isHotel = true))
            add(Hotel(name = "Hotel pacote", stars = 5, isPackage = true))
        }

        every { mockHotelsRepository.fetchHotels() } returns just(hotels)

        val itemsObserver = TestObserver.create<ListViewModel>()

        viewModel.items.subscribe(itemsObserver)

        val disposable = viewModel.loadItemsCommand.execute().subscribe()

        itemsObserver.await(1000L, TimeUnit.MILLISECONDS)

        val listViewModel = itemsObserver.values()[0]

        disposable.dispose()

        assertTrue { listViewModel.size == 6 }
        assertEquals("5", (listViewModel[0] as HotelSectionListItemViewModel).name)
        assertEquals("Hotel star 5", (listViewModel[1] as HotelListItemViewModel).name)
        assertEquals("3", (listViewModel[2] as HotelSectionListItemViewModel).name)
        assertEquals("Hotel star 3", (listViewModel[3] as HotelListItemViewModel).name)
        assertEquals(null, (listViewModel[4] as PackageSectionListItemViewModel).name)
        assertEquals("Hotel pacote", (listViewModel[5] as PackageListItemViewModel).name)
    }
}