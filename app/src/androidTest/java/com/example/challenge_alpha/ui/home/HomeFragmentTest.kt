package com.example.challenge_alpha.ui.home

import android.view.KeyEvent
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.challenge_alpha.MainActivity
import com.example.challenge_alpha.R
import org.junit.*
import org.junit.runner.RunWith
import com.example.challenge_alpha.util.CustomAssertions.Companion.hasItemCount
import com.example.challenge_alpha.util.DataBindingIdlingResourceRule


@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeFragmentTest {


    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Rule
    @JvmField
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule(activityRule)

    @Test
    fun searchView() {

        onView(withId(R.id.action_search)).perform(click())
        onView(isAssignableFrom(AutoCompleteTextView::class.java))
            .perform(typeText("buzios"), pressKey(KeyEvent.KEYCODE_ENTER))
        onView(withId(R.id.fragment_results)).check(matches(isDisplayed()))


        val recyclerCount = recyclerCount(R.id.recyclerResult) ?: 0
        onView(withId(R.id.recyclerResult))
            .check(hasItemCount(recyclerCount))


        if (recyclerCount > 0) {
            onView(withId(R.id.recyclerResult))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        click()
                    )
                )
            onView(withId(R.id.fragment_resultDetail)).check(matches(isDisplayed()))
            onView(withId(R.id.recyclerGallery_detail)).check(matches(isDisplayed()))
            onView(withId(R.id.name_detail)).check(matches(isDisplayed()))
            onView(withId(R.id.price_detail)).check(matches(isDisplayed()))

        }

    }

    @Test
    fun lastSeen() {


       // onView(withId(R.id.lastSeen_title)).check(matches(isDisplayed()))

        val recyclerCount = recyclerCount(R.id.lastSeen_display) ?: 0
        onView(withId(R.id.lastSeen_display))
            .check(hasItemCount(recyclerCount))


        if (recyclerCount > 0) {
            onView(withId(R.id.lastSeen_display))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        click()
                    )
                )
            onView(withId(R.id.fragment_resultDetail)).check(matches(isDisplayed()))
            onView(withId(R.id.recyclerGallery_detail)).check(matches(isDisplayed()))
            onView(withId(R.id.name_detail)).check(matches(isDisplayed()))
            onView(withId(R.id.price_detail)).check(matches(isDisplayed()))

        }

    }

    private fun recyclerCount(viewId: Int): Int? {

        val recyclerView = activityRule.activity.findViewById(viewId) as RecyclerView
        return recyclerView.adapter?.itemCount

    }


}