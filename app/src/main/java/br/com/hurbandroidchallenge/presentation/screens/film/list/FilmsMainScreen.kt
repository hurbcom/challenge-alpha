package br.com.hurbandroidchallenge.presentation.screens.film.list

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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.R
import br.com.hurbandroidchallenge.commom.extension.containsIgnoringAccent
import br.com.hurbandroidchallenge.data.mapper.films.toModel
import br.com.hurbandroidchallenge.presentation.compose.components.lazy_list.ItemList
import br.com.hurbandroidchallenge.presentation.compose.navigation.Screens
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.error.DefaultErrorScreen
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.loading.DefaultLoadingScreen
import br.com.hurbandroidchallenge.presentation.compose.widgets.top_bar.SearchTopBar
import br.com.hurbandroidchallenge.presentation.compose.widgets.top_bar.TopBar
import br.com.hurbandroidchallenge.presentation.compose.widgets.top_bar.TopBarIcon
import br.com.hurbandroidchallenge.presentation.model.StateUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmsMainScreen(
    navHostController: NavHostController,
    viewModel: FilmsViewModel,
) {
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val filmsUI = viewModel.filmsUI.value
    var isSearching by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            if (isSearching) {
                SearchTopBar(
                    searchText = searchText,
                    placeholderText = "Título do filme",
                    onClearClick = { searchText = "" },
                    onBackPressed = { isSearching = false },
                    onSearchTextChanged = {
                        searchText = it
                    }
                )
            } else {
                TopBar(
                    title = stringResource(id = R.string.home_categories_films),
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
            viewModel.filmsState.collectAsState().value.let { response ->
                when (response) {
                    is StateUI.Error -> DefaultErrorScreen(message = response.message)
                    is StateUI.Idle -> Unit
                    is StateUI.Processed -> {
                        val filteredFilms = filmsUI.films.filter {
                            if (searchText.isNotBlank()) {
                                it.title.containsIgnoringAccent(searchText, ignoreCase = true)
                            } else true
                        }
                        ItemList(
                            categoryItems = filteredFilms.map { film ->
                                film.toModel()
                            },
                            onItemClick = { url ->
                                navHostController.navigate(Screens.FilmDetail.routeWithArgument(url))
                            }
                        )
                    }
                    is StateUI.Processing -> DefaultLoadingScreen()
                }
            }
        }
    }
}