package br.com.hurbandroidchallenge.presentation.screens.film.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.commom.extension.toRoman
import br.com.hurbandroidchallenge.data.mapper.films.toModel
import br.com.hurbandroidchallenge.presentation.compose.components.CategoryItemDetail
import br.com.hurbandroidchallenge.presentation.compose.components.OtherCategoryCard
import br.com.hurbandroidchallenge.presentation.compose.navigation.Screens
import br.com.hurbandroidchallenge.presentation.compose.widgets.image.SmallCategoryItemImage
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.error.DefaultErrorScreen
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.error.DefaultErrorText
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.loading.DefaultLoading
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.loading.DefaultLoadingScreen
import br.com.hurbandroidchallenge.presentation.compose.widgets.top_bar.TopBar
import br.com.hurbandroidchallenge.presentation.model.StateUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDetailMainScreen(
    navHostController: NavHostController,
    viewModel: FilmDetailViewModel
) {
    val filmUI = viewModel.filmUI.value
    Scaffold(
        topBar = {
            TopBar(
                title = if (filmUI.film != null) "Episode ${filmUI.film.episodeId.toRoman()}" else "",
                onBackPressed = { navHostController.navigateUp() }
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
                    OtherCategoryCard(name = "Opening") {
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
                    OtherCategoryCard(name = "Characters") {
                        viewModel.charactersState.collectAsState().value.let { response ->
                            when (response) {
                                is StateUI.Error -> DefaultErrorText()
                                is StateUI.Idle -> Unit
                                is StateUI.Processed -> {
                                    LazyRow(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentPadding = PaddingValues(all = 16.dp)
                                    ) {
                                        items(filmUI.characters) { character ->
                                            val firstName = character.name
                                            SmallCategoryItemImage(
                                                text = firstName,
                                                textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                                image = character.image,
                                                onClick = {
                                                    navHostController.navigate(
                                                        Screens.CharacterDetail.routeWithArgument(
                                                            character.url
                                                        )
                                                    )
                                                }
                                            )
                                        }
                                    }
                                }
                                is StateUI.Processing -> {
                                    DefaultLoading(
                                        modifier = Modifier.fillMaxWidth(),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                    OtherCategoryCard(name = "Planets") {
                        viewModel.planetsState.collectAsState().value.let { response ->
                            when (response) {
                                is StateUI.Error -> DefaultErrorText()
                                is StateUI.Idle -> Unit
                                is StateUI.Processed -> {
                                    LazyRow(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentPadding = PaddingValues(all = 16.dp)
                                    ) {
                                        items(filmUI.planets) { planet ->
                                            SmallCategoryItemImage(
                                                text = planet.name,
                                                textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                                image = planet.image,
                                                onClick = {
                                                    navHostController.navigate(
                                                        Screens.PlanetDetail.routeWithArgument(planet.url)
                                                    )
                                                }
                                            )
                                        }
                                    }
                                }
                                is StateUI.Processing -> {
                                    DefaultLoading(
                                        modifier = Modifier.fillMaxWidth(),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}