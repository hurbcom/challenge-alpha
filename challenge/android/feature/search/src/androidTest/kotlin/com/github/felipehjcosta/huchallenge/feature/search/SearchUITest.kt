package com.github.felipehjcosta.huchallenge.feature.search

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.github.felipehjcosta.huchallenge.MockMainApplication
import com.github.felipehjcosta.huchallenge.utils.readAsset
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.core.AllOf.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class SearchUITest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<SearchActivity>(SearchActivity::class.java, false, false)

    @Inject
    lateinit var mockWebServer: MockWebServer

    init {
        (InstrumentationRegistry.getInstrumentation()
                .targetContext
                .applicationContext as MockMainApplication)
                .applicationComponent.inject(this)
    }

    @Before
    fun setUp() {
        mockWebServer.setDispatcher(object : Dispatcher() {
            override fun dispatch(request: RecordedRequest?): MockResponse {
                val pathWithQuery = "${request!!.requestUrl.encodedPath()}?${request.requestUrl.encodedQuery()}"
                return when (pathWithQuery) {
                    "/api?q=Rio%20de%20Janeiro" -> MockResponse()
                            .setResponseCode(200)
                            .addHeader("Content-Type", "application/json")
                            .setBody(readAsset("results.json"))
                    else -> MockResponse().setResponseCode(404)
                }
            }
        })

        activityRule.launchActivity(Intent())
    }

    @Test
    fun whenLaunchedThenTheFirstHotelIsShown() {
        onView(allOf(withId(R.id.hotels_recycler_view),
                hasDescendant(withText("Hotel Vilamar Copacabana"))))
                .check(matches(isDisplayed()))
    }
}