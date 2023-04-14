package br.com.hurbandroidchallenge.presentation.screens.character.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.commom.extension.containsIgnoringAccent
import br.com.hurbandroidchallenge.data.local.preferences.PreferencesWrapper
import br.com.hurbandroidchallenge.data.mapper.characters.toModel
import br.com.hurbandroidchallenge.presentation.compose.components.lazy_list.PagedItemList
import br.com.hurbandroidchallenge.presentation.compose.navigation.Screens
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.error.DefaultErrorScreen
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.loading.DefaultLoadingScreen
import br.com.hurbandroidchallenge.presentation.compose.widgets.swipe_refresh.DefaultSwipeRefresh
import br.com.hurbandroidchallenge.presentation.compose.widgets.top_bar.SearchTopBar
import br.com.hurbandroidchallenge.presentation.compose.widgets.top_bar.TopBar
import br.com.hurbandroidchallenge.presentation.compose.widgets.top_bar.TopBarIcon
import br.com.hurbandroidchallenge.presentation.model.StateUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailMainScreen(
    navHostController: NavHostController,
    viewModel: CharactersViewModel,
) {
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    var isSearching by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            if (isSearching) {
                SearchTopBar(
                    searchText = searchText,
                    placeholderText = "Character name",
                    onClearClick = { searchText = "" },
                    onBackPressed = { isSearching = false },
                    onSearchTextChanged = {
                        searchText = it
                    }
                )
            } else {
                TopBar(
                    title = "Characters",
                    onBackPressed = { navHostController.navigateUp() },
                    scrollBehavior = scrollBehavior,
                    actions = {
                        TopBarIcon(
                            onClick = { isSearching = true },
                            imageVector = Icons.Default.Search
                        )
                    }
                )
            }
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
        ) {
            viewModel.charactersState.collectAsState().value.let { response ->
                when (response) {
                    is StateUI.Error -> {
                        DefaultErrorScreen(
                            message = response.message,
                            refresh = {
                                viewModel.loadCharacters()
                            }
                        )
                    }
                    is StateUI.Idle -> Unit
                    is StateUI.Processed -> {
                        CharactersScreen(
                            viewModel = viewModel,
                            navHostController = navHostController,
                            searchText = searchText,
                            isSearching = isSearching
                        )
                    }
                    is StateUI.Processing -> DefaultLoadingScreen()
                }
            }
        }
    }
}

@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel,
    navHostController: NavHostController,
    searchText: String,
    isSearching: Boolean,
) {
    val charactersUI = viewModel.characters.value
    val loadMoreResponse = viewModel.loadMoreState.collectAsState().value
    val filteredCharacters = charactersUI.characters.filter {
        if (searchText.isNotBlank()) {
            it.name.containsIgnoringAccent(searchText, ignoreCase = true)
        } else true
    }
    PagedItemList(
        items = filteredCharacters.map { character ->
            character.toModel()
        },
        onItemClick = { url ->
            navHostController.navigate(Screens.CharacterDetail.routeWithArgument(url))
        },
        isLoading = loadMoreResponse.loading(),
        loadMore = {
            if (!isSearching && !PreferencesWrapper.getInstance().isCharactersUpToDate()) {
                viewModel.loadMoreCharacters()
            }
        }
    )
}