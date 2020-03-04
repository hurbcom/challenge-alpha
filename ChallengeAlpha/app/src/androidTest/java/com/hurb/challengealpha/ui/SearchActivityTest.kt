package com.hurb.challengealpha.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.hurb.challengealpha.R
import com.hurb.challengealpha.ui.search.SearchActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class SearchActivityTest {

    @Rule
    @JvmField
    val rule: ActivityTestRule<SearchActivity> = ActivityTestRule(SearchActivity::class.java)
    private var mIdlingResource: IdlingResource? = null
    private val editTextString = "Buzios"

    @Before
    fun registerIdlingResource() {
        val activityScenario =
            ActivityScenario.launch(SearchActivity::class.java)
        activityScenario.onActivity { activity ->
            mIdlingResource = activity.getIdlingResource()
            IdlingRegistry.getInstance().register(mIdlingResource)
        }
    }

    @After
    fun unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource)
        }
    }

    @Test
    fun testEditText() {
        onView(withId(R.id.search_et)).perform(typeText(editTextString))
        closeSoftKeyboard()
        onView(withId(R.id.search_et)).check(matches(ViewMatchers.withText(editTextString)))
    }

    @Test
    fun testClearButton() {
        onView(withId(R.id.search_et)).perform(typeText(editTextString))
        closeSoftKeyboard()
        onView(withId(R.id.search_et)).check(matches(ViewMatchers.withText(editTextString)))
        onView(withId(R.id.clear_bt)).perform(click())
        onView(withId(R.id.search_et)).check(matches(ViewMatchers.withText("")))
    }
}