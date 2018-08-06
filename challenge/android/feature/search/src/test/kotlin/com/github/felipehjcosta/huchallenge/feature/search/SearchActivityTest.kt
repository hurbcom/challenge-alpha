package com.github.felipehjcosta.huchallenge.feature.search

import android.support.v7.widget.RecyclerView
import com.github.felipehjcosta.huchallenge.base.imageloader.ImageLoader
import com.github.felipehjcosta.huchallenge.feature.TestStubApplication
import com.github.felipehjcosta.huchallenge.feature.search.viewmodel.ListViewModel
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
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
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

    private val mockImageLoader = mockk<ImageLoader>(relaxed = true)

    private val searchActivityController: ActivityController<SearchActivity> = Robolectric
            .buildActivity(SearchActivity::class.java)
            .create()
            .apply {
                get().viewModel = mockViewModel
                get().imageLoader = mockImageLoader
            }

    private val hotelsListViewModel = mockk<ListViewModel>(relaxed = true)

    @Test
    fun ensureItemsAreDisplayedAfterStart() {
        every { mockViewModel.items } returns just(hotelsListViewModel)
        searchActivityController.start()
                .postCreate(null)
                .resume()
                .visible()

        val recyclerView = searchActivityController.get().findViewById<RecyclerView>(R.id.hotels_recycler_view)
        assertNotNull(recyclerView.adapter)
    }

    @Test
    fun ensureViewModelPropsAreDisposeAfterStopped() {
        val itemsSubject = BehaviorSubject.createDefault(hotelsListViewModel).apply {
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