package br.com.hurbandroidchallenge.presentation.screens.film.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.commom.extension.toRoman
import br.com.hurbandroidchallenge.data.mapper.films.toModel
import br.com.hurbandroidchallenge.presentation.compose.components.item_model.CategoryItemDetail
import br.com.hurbandroidchallenge.presentation.compose.components.item_model.CategoryItemsExpandableList
import br.com.hurbandroidchallenge.presentation.compose.components.card.DefaultExpandableCard
import br.com.hurbandroidchallenge.presentation.compose.navigation.Screens
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.error.DefaultErrorScreen
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.loading.DefaultLoadingScreen
import br.com.hurbandroidchallenge.presentation.compose.widgets.top_bar.TopBar
import br.com.hurbandroidchallenge.presentation.compose.widgets.top_bar.TopBarIcon
import br.com.hurbandroidchallenge.presentation.model.StateUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDetailMainScreen(
    navHostController: NavHostController,
    viewModel: FilmDetailViewModel,
) {
    val filmUI = viewModel.filmUI.value
    Scaffold(
        topBar = {
            TopBar(
                title = if (filmUI.film != null) "Episode ${filmUI.film.episodeId.toRoman()}" else "",
                onBackPressed = { navHostController.navigateUp() },
                actions = {
                    TopBarIcon(
                        onClick = {
                            viewModel.favorite()
                        },
                        imageVector = if (filmUI.favorite)  Icons.Default.Star else  Icons.Default.StarBorder
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
        ) {
            viewModel.filmState.collectAsState().value.let { response ->
                when (response) {
                    is StateUI.Error -> DefaultErrorScreen()
                    is StateUI.Idle -> Unit
                    is StateUI.Processed -> {
                        FilmDetailScreen(
                            viewModel = viewModel,
                            navHostController = navHostController
                        )
                    }
                    is StateUI.Processing -> DefaultLoadingScreen()
                }
            }
        }
    }
}

@Composable
fun FilmDetailScreen(
    viewModel: FilmDetailViewModel,
    navHostController: NavHostController,
) {
    val filmUI = viewModel.filmUI.value
    filmUI.film?.let { film ->
        val filmModel = film.toModel()
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            CategoryItemDetail(itemModel = filmModel) {
                Column(
                    modifier = Modifier.padding(all = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    DefaultExpandableCard(name = "Opening") {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 32.dp, vertical = 16.dp),
                            text = film.openingCrawl,
                            style = MaterialTheme.typography.titleMedium.copy(
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                    CategoryItemsExpandableList(
                        name = "Characters",
                        listState = viewModel.charactersState.collectAsState().value,
                        onClick = { url ->
                            navHostController.navigate(
                                Screens.CharacterDetail.routeWithArgument(url)
                            )
                        }
                    )
                    CategoryItemsExpandableList(
                        name = "Planets",
                        listState = viewModel.planetsState.collectAsState().value,
                        onClick = { url ->
                            navHostController.navigate(
                                Screens.PlanetDetail.routeWithArgument(url)
                            )
                        }
                    )
                }
            }
        }
    }
}