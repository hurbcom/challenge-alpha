package com.github.felipehjcosta.huchallenge.feature.hotels

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class HotelsViewModelTest {

    private val viewModel = HotelsViewModel()

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

        val itemsObserver = TestObserver.create<List<String>>()

        viewModel.items.subscribe(itemsObserver)

        val disposable = viewModel.loadItemsCommand.execute().subscribe()

        itemsObserver.await(1000L, TimeUnit.MILLISECONDS)

        itemsObserver.assertValue { it[0] == hotelName }

        disposable.dispose()
    }
}