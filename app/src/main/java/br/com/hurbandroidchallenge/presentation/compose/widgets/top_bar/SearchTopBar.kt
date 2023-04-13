package br.com.hurbandroidchallenge.presentation.compose.widgets.top_bar

import androidx.activity.compose.BackHandler
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import br.com.hurbandroidchallenge.presentation.compose.widgets.edit_text.SearchEditText

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    searchText: String,
    placeholderText: String,
    onClearClick: () -> Unit,
    onBackPressed: () -> Unit,
    onSearchTextChanged: (String) -> Unit,

    ) {
    val (showClearButton, setShowClearButton) = remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    BackHandler {
        onBackPressed()
    }
    TopBar(title = "") {
        SearchEditText(
            searchText = searchText,
            onSearchTextChanged = onSearchTextChanged,
            placeholder = placeholderText,
            showClearButton = showClearButton,
            setShowClearButton = setShowClearButton,
            keyboardController = keyboardController,
            focusRequester = focusRequester,
            onClearClick = onClearClick,
            onBackPressed = onBackPressed
        )
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}