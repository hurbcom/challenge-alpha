package br.com.mdr.starwars

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import br.com.mdr.starwars.navigation.HomeNavGraph
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CategoriesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            HomeNavGraph(navController = navController)
        }
    }

    @Test
    fun givenCategoriesScreen_whenItAppears_thenCheckCategoriesIsLoaded() {
        composeTestRule
            .onNodeWithContentDescription("Shimmer")
            .assertIsDisplayed()
    }
}
