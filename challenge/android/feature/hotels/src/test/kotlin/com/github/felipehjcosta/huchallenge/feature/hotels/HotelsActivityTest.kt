package com.github.felipehjcosta.huchallenge.feature.hotels

import android.support.v7.widget.RecyclerView
import com.github.felipehjcosta.huchallenge.feature.TestStubApplication
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
class HotelsActivityTest {

    private val mockViewModel = mockk<HotelsViewModel>(relaxed = true)

    private val hotelsActivityController: ActivityController<HotelsActivity> = Robolectric
            .buildActivity(HotelsActivity::class.java)
            .create()
            .apply { get().viewModel = mockViewModel }

    private val items = listOf("Awesome Hotel", "New Hotel")


    @Test
    fun ensureItemsAreDisplayedAfterStart() {
        every { mockViewModel.items } returns just(items)
        hotelsActivityController.start()
                .postCreate(null)
                .resume()
                .visible()

        val recyclerView = hotelsActivityController.get().findViewById<RecyclerView>(R.id.hotels_recycler_view)
        assertEquals(2, recyclerView.adapter.itemCount)
    }

    @Test
    fun ensureViewModelPropsAreDisposeAfterStopped() {
        val itemsSubject = BehaviorSubject.createDefault(items).apply {
            every { mockViewModel.items } returns this
        }

        hotelsActivityController.start()
                .postCreate(null)
                .resume()
                .visible()

        assertTrue { itemsSubject.hasObservers() }

        hotelsActivityController.pause().stop()

        assertFalse { itemsSubject.hasObservers() }
    }
}