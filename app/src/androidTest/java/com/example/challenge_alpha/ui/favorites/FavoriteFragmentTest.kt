package com.example.challenge_alpha.ui.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.challenge_alpha.R
import com.example.challenge_alpha.data.ResultDetailRelation
import com.example.challenge_alpha.model.*
import com.example.challenge_alpha.testing.TestActivity
import com.example.challenge_alpha.ui.resultDetail.ResultDetailFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.challenge_alpha.util.CustomAssertions
import com.example.challenge_alpha.util.DataBindingIdlingResourceRule
import com.example.challenge_alpha.util.RecyclerViewMatcher
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
@LargeTest
class FavoriteFragmentTest {

    @get:Rule
    var activityRule = ActivityTestRule(TestActivity::class.java, true, true)

    @Rule
    @JvmField
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule(activityRule)


    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val favoriteFragment = TestFavoriteFragment()
    private lateinit var favoriteViewModel: FavoritesViewModel


    private val liveFavorite = MutableLiveData<List<ResultDetailRelation>>()


    @Before
    fun setup() {
        favoriteViewModel = mock(FavoritesViewModel::class.java)

        favoriteFragment.viewModelFactory = ViewModelUtil.createFor(favoriteViewModel)
        activityRule.activity.setFragment(favoriteFragment)

    }

    @Test
    fun favoritesMock() {

        `when`(favoriteViewModel.getFavorites()).thenReturn(liveFavorite)

        val favorite = mutableListOf(ResultDetailRelation().apply {
            resultDetail = ResultDetail(
                "123", name = "123name",
                address = ResultDetailAddress(city = "123", state = "123")
            )
            resultDetailAmenities = listOf(ResultDetailAmenities("123"))
            resultDetailGallery = listOf(ResultDetailGallery("123"))
            resultDetailTaxes = listOf(ResultDetailTaxes("123"))
        })
        liveFavorite.postValue(favorite)

        Espresso.onView(ViewMatchers.withId(R.id.fragment_favorites))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


        val recyclerCount = recyclerCount() ?: 0
        Espresso.onView(ViewMatchers.withId(R.id.recyclerFavorite))
            .check(CustomAssertions.hasItemCount(recyclerCount))


        Espresso.onView(listMatcher().atPosition(0)).check(
            ViewAssertions.matches(
                ViewMatchers.hasDescendant(
                    ViewMatchers.withText("123name")
                )
            )
        )
        Espresso.onView(listMatcher().atPosition(0)).check(
            ViewAssertions.matches(
                ViewMatchers.hasDescendant(
                    ViewMatchers.withText("123, 123")
                )
            )
        )

        Espresso.onView(ViewMatchers.withId(R.id.name_results))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.price_results))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


    }

    @Test
    fun favorites() {


        Espresso.onView(ViewMatchers.withId(R.id.navigation_favorites)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.fragment_favorites))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


        val recyclerCount = recyclerCount() ?: 0
        Espresso.onView(ViewMatchers.withId(R.id.recyclerFavorite))
            .check(CustomAssertions.hasItemCount(recyclerCount))


        if (recyclerCount > 0) {
            Espresso.onView(ViewMatchers.withId(R.id.recyclerFavorite))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        ViewActions.click()
                    )
                )
            Espresso.onView(ViewMatchers.withId(R.id.fragment_resultDetail))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            Espresso.onView(ViewMatchers.withId(R.id.recyclerGallery_detail))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            Espresso.onView(ViewMatchers.withId(R.id.name_detail))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            Espresso.onView(ViewMatchers.withId(R.id.price_detail))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        }

    }

    private fun recyclerCount(): Int? {

        val recyclerView = activityRule.activity.findViewById(R.id.recyclerFavorite) as RecyclerView
        return recyclerView.adapter?.itemCount

    }
}


private fun listMatcher(): RecyclerViewMatcher {
    return RecyclerViewMatcher(R.id.recyclerFavorite)
}

class TestFavoriteFragment : FavoritesFragment() {
}

object ViewModelUtil {
    fun <T : ViewModel> createFor(model: T): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(model.javaClass)) {
                    @Suppress("UNCHECKED_CAST")
                    return model as T
                }
                throw IllegalArgumentException("unexpected model class $modelClass")
            }
        }
    }
}