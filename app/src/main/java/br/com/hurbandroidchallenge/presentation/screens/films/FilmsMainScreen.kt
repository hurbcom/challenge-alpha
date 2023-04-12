package br.com.hurbandroidchallenge.presentation.screens.films

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.R
import br.com.hurbandroidchallenge.commom.extension.toRoman
import br.com.hurbandroidchallenge.data.remote.config.ApiUrls
import br.com.hurbandroidchallenge.domain.model.ItemModel
import br.com.hurbandroidchallenge.presentation.compose.components.lazy_list.ItemList
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.error.DefaultErrorScreen
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.loading.DefaultLoadingScreen
import br.com.hurbandroidchallenge.presentation.compose.widgets.top_bar.TopBar
import br.com.hurbandroidchallenge.presentation.model.StateUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmsMainScreen(
    navHostController: NavHostController,
    viewModel: FilmsViewModel
) {
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val filmsUI = viewModel.filmsUI.value
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.home_categories_films),
                onBackPressed = { navHostController.navigateUp() },
                scrollBehavior = scrollBehavior
            )
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
                        ItemList(
                            categoryItems = filmsUI.films.map { film ->
                                ItemModel(
                                    image = "${ApiUrls.imageBaseUrl}films/${film.id}.jpg",
                                    fields = listOf(
                                        "Episode ${film.episodeId.toRoman()}: ${film.title}" to "",
                                        "Diretor" to film.director,
                                        "Data de lançamento" to film.releaseDate
                                    ),
                                    url = film.url
                                )
                            },
                            onItemClick = {

                            },
                            aspectRatio = 4f / 5.5f
                        )
                    }
                    is StateUI.Processing -> DefaultLoadingScreen()
                }
            }
        }
    }
}