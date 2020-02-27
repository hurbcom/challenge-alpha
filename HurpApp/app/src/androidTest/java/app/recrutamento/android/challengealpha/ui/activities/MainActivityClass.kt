package app.recrutamento.android.challengealpha.ui.activities


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import app.recrutamento.android.challengealpha.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityClass {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun hotelListFragmentTest() {
        val floatingActionButton = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.floatingActionMenu),
                        childAtPosition(
                            withClassName(`is`("android.widget.RelativeLayout")),
                            4
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())

       Thread.sleep(250)

        val floatingActionButton2 = onView(
            allOf(
                withId(R.id.optStarFive),
                childAtPosition(
                    allOf(
                        withId(R.id.floatingActionMenu),
                        childAtPosition(
                            withClassName(`is`("android.widget.RelativeLayout")),
                            4
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        floatingActionButton2.perform(click())

        val floatingActionButton3 = onView(
            allOf(
                withId(R.id.optStarFour),
                childAtPosition(
                    allOf(
                        withId(R.id.floatingActionMenu),
                        childAtPosition(
                            withClassName(`is`("android.widget.RelativeLayout")),
                            4
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        floatingActionButton3.perform(click())

        val floatingActionButton4 = onView(
            allOf(
                withId(R.id.optStarThree),
                childAtPosition(
                    allOf(
                        withId(R.id.floatingActionMenu),
                        childAtPosition(
                            withClassName(`is`("android.widget.RelativeLayout")),
                            4
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        floatingActionButton4.perform(click())

        val floatingActionButton5 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.floatingActionMenu),
                        childAtPosition(
                            withClassName(`is`("android.widget.RelativeLayout")),
                            4
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        floatingActionButton5.perform(click())

        Thread.sleep(250)

        val appCompatImageButton = onView(
            allOf(
                withId(R.id.img_grid_on_off),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val appCompatImageButton2 = onView(
            allOf(
                withId(R.id.img_grid_on_off),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatImageButton2.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                    && view == parent.getChildAt(position)
            }
        }
    }
}
