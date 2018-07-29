package com.github.felipehjcosta.huchallenge.feature.hotels

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.core.AllOf.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HotelsActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<HotelsActivity>(HotelsActivity::class.java, false, false)

    @Before
    fun setUp() {
        activityRule.launchActivity(Intent())
    }

    @Test
    fun name() {
        onView(allOf(withId(R.id.hotels_recycler_view),
                hasDescendant(withText("Hotel Vilamar Copacabana"))))
                .check(matches(isDisplayed()))
    }
}