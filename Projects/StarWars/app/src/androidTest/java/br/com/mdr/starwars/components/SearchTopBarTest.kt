package br.com.mdr.starwars.components

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import br.com.mdr.starwars.common.Constants.CLOSE_BUTTON
import br.com.mdr.starwars.common.Constants.SEARCH_COMPONENT
import br.com.mdr.starwars.common.Constants.SEARCH_TEXT_FIELD
import br.com.mdr.starwars.ui.presentation.components.SearchTopBar
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class SearchTopBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val query = "testQuery"

    @Test
    fun givenSearchWidget_whenAddInputText_thenAssertInputTextValue() {
        val text = mutableStateOf("")
        composeTestRule.setContent {
            SearchTopBar(
                text = text.value,
                onTextChange = {
                    text.value = it
                },
                onCloseClicked = {},
                onSearchClicked = {}
            )
        }
        composeTestRule.onNodeWithContentDescription(SEARCH_TEXT_FIELD)
            .performTextInput(query)
        composeTestRule.onNodeWithContentDescription(SEARCH_TEXT_FIELD)
            .assertTextEquals(query)
        assertEquals(query, text.value)
    }

    @Test
    fun openSearchWidget_addInputText_pressCloseButtonOnce_assertEmptyInputText() {
        val text = mutableStateOf("")
        composeTestRule.setContent {
            SearchTopBar(
                text = text.value,
                onTextChange = {
                    text.value = it
                },
                onCloseClicked = {},
                onSearchClicked = {}
            )
        }
        composeTestRule.onNodeWithContentDescription(SEARCH_TEXT_FIELD)
            .performTextInput(query)
        composeTestRule.onNodeWithContentDescription(CLOSE_BUTTON)
            .performClick()
        composeTestRule.onNodeWithContentDescription(SEARCH_TEXT_FIELD)
            .assertTextContains("")
    }

    @Test
    fun openSearchWidget_addInputText_pressCloseButtonTwice_assertClosedState() {
        val text = mutableStateOf("")
        val searchWidgetShown = mutableStateOf(true)
        composeTestRule.setContent {
            if(searchWidgetShown.value){
                SearchTopBar(
                    text = text.value,
                    onTextChange = {
                        text.value = it
                    },
                    onCloseClicked = {
                        searchWidgetShown.value = false
                    },
                    onSearchClicked = {}
                )
            }
        }
        composeTestRule.onNodeWithContentDescription(SEARCH_TEXT_FIELD)
            .performTextInput(query)
        composeTestRule.onNodeWithContentDescription(CLOSE_BUTTON)
            .performClick()
        composeTestRule.onNodeWithContentDescription(CLOSE_BUTTON)
            .performClick()
        composeTestRule.onNodeWithContentDescription(SEARCH_COMPONENT)
            .assertDoesNotExist()
    }

    @Test
    fun openSearchWidget_pressCloseButtonOnceWhenInputIsEmpty_assertClosedState() {
        val text = mutableStateOf("")
        val searchWidgetShown = mutableStateOf(true)
        composeTestRule.setContent {
            if(searchWidgetShown.value){
                SearchTopBar(
                    text = text.value,
                    onTextChange = {
                        text.value = it
                    },
                    onCloseClicked = {
                        searchWidgetShown.value = false
                    },
                    onSearchClicked = {}
                )
            }
        }
        composeTestRule.onNodeWithContentDescription(CLOSE_BUTTON)
            .performClick()
        composeTestRule.onNodeWithContentDescription(SEARCH_COMPONENT)
            .assertDoesNotExist()
    }
}