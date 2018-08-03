package com.github.felipehjcosta.huchallenge.feature.hotels

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

class HotelsViewModelTest {

    private val mockHotelsRepository = mockk<HotelsRepository>()

    private val viewModel = HotelsViewModel(mockHotelsRepository)

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
        every { mockHotelsRepository.fetchHotels() } returns just(listOf(hotelName))

        val itemsObserver = TestObserver.create<List<String>>()

        viewModel.items.subscribe(itemsObserver)

        val disposable = viewModel.loadItemsCommand.execute().subscribe()

        itemsObserver.await(1000L, TimeUnit.MILLISECONDS)

        itemsObserver.assertValue { it[0] == hotelName }

        disposable.dispose()
    }
}