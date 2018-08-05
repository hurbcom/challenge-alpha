package com.github.felipehjcosta.huchallenge.feature.search

import android.support.v7.widget.RecyclerView
import com.github.felipehjcosta.huchallenge.feature.TestStubApplication
import com.github.felipehjcosta.huchallenge.feature.search.viewmodel.SearchViewModel
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable.just
import io.reactivex.subjects.BehaviorSubject
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
@Config(
        application = TestStubApplication::class,
        manifest = Config.NONE,
        constants = BuildConfig::class,
        sdk = [21]
)
class SearchActivityTest {

    private val mockViewModel = mockk<SearchViewModel>(relaxed = true)

    private val searchActivityController: ActivityController<SearchActivity> = Robolectric
            .buildActivity(SearchActivity::class.java)
            .create()
            .apply { get().viewModel = mockViewModel }

    private val items = listOf("Awesome Hotel", "New Hotel")


    @Test
    fun ensureItemsAreDisplayedAfterStart() {
        every { mockViewModel.items } returns just(items)
        searchActivityController.start()
                .postCreate(null)
                .resume()
                .visible()

        val recyclerView = searchActivityController.get().findViewById<RecyclerView>(R.id.hotels_recycler_view)
        assertEquals(2, recyclerView.adapter.itemCount)
    }

    @Test
    fun ensureViewModelPropsAreDisposeAfterStopped() {
        val itemsSubject = BehaviorSubject.createDefault(items).apply {
            every { mockViewModel.items } returns this
        }

        searchActivityController.start()
                .postCreate(null)
                .resume()
                .visible()

        assertTrue { itemsSubject.hasObservers() }

        searchActivityController.pause().stop()

        assertFalse { itemsSubject.hasObservers() }
    }
}