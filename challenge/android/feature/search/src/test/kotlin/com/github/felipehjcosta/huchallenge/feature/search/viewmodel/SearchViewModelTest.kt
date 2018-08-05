package com.github.felipehjcosta.huchallenge.feature.search.viewmodel

import com.github.felipehjcosta.huchallenge.base.hotels.Hotel
import com.github.felipehjcosta.huchallenge.base.hotels.HotelsRepository
import com.github.felipehjcosta.huchallenge.feature.search.viewmodel.SearchViewModel
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
    fun subscribedToItemsWhenExecuteLoadItemsCommandThenReturnItems() {

        val hotelName = "Hotel Vilamar Copacabana"
        val hotel = Hotel(hotelName)
        every { mockHotelsRepository.fetchHotels() } returns just(listOf(hotel))

        val itemsObserver = TestObserver.create<HotelsListViewModel>()

        viewModel.items.subscribe(itemsObserver)

        val disposable = viewModel.loadItemsCommand.execute().subscribe()

        itemsObserver.await(1000L, TimeUnit.MILLISECONDS)

        itemsObserver.assertValue { it[0]!!.name == hotelName }

        disposable.dispose()
    }
}