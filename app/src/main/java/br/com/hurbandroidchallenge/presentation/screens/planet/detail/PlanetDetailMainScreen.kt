package br.com.hurbandroidchallenge.presentation.screens.planet.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.commom.extension.toRoman
import br.com.hurbandroidchallenge.data.mapper.planets.toModel
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
fun PlanetDetailMainScreen(
    navHostController: NavHostController,
    viewModel: PlanetDetailViewModel,
) {
    val planetUI = viewModel.planetUI.value
    Scaffold(
        topBar = {
            TopBar(
                title = planetUI.planet?.name.orEmpty(),
                onBackPressed = { navHostController.navigateUp() }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
        ) {
            viewModel.planetState.collectAsState().value.let { response ->
                when (response) {
                    is StateUI.Error -> DefaultErrorScreen()
                    is StateUI.Idle -> Unit
                    is StateUI.Processed -> {
                        PlanetDetailScreen(
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
fun PlanetDetailScreen(
    navHostController: NavHostController,
    viewModel: PlanetDetailViewModel,
) {
    val planetUI = viewModel.planetUI.value
    planetUI.planet?.let { planet ->
        val planetModel = planet.toModel()
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            CategoryItemDetail(itemModel = planetModel) {
                Column(
                    modifier = Modifier.padding(all = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OtherCategoryCard(name = "Movies") {
                        viewModel.filmsState.collectAsState().value.let { response ->
                            when (response) {
                                is StateUI.Error -> DefaultErrorText()
                                is StateUI.Idle -> Unit
                                is StateUI.Processed -> {
                                    if (planetUI.films.isEmpty()) {
                                        DefaultErrorText(message = "This planet isn't related with any movie")
                                    } else {
                                        LazyRow(
                                            modifier = Modifier.fillMaxWidth(),
                                            contentPadding = PaddingValues(all = 16.dp)
                                        ) {
                                            items(planetUI.films) { film ->
                                                SmallCategoryItemImage(
                                                    text = "Episode ${film.episodeId.toRoman()}",
                                                    textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                                    image = film.image,
                                                    onClick = {
                                                        navHostController.navigate(
                                                            Screens.FilmDetail.routeWithArgument(
                                                                film.url
                                                            )
                                                        )
                                                    }
                                                )
                                            }
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
                    OtherCategoryCard(name = "Characters") {
                        viewModel.charactersState.collectAsState().value.let { response ->
                            when (response) {
                                is StateUI.Error -> DefaultErrorText()
                                is StateUI.Idle -> Unit
                                is StateUI.Processed -> {
                                    if (planetUI.characters.isEmpty()) {
                                        DefaultErrorText(message = "This planet isn't related with any movie")
                                    } else {
                                        LazyRow(
                                            modifier = Modifier.fillMaxWidth(),
                                            contentPadding = PaddingValues(all = 16.dp)
                                        ) {
                                            items(planetUI.characters) { character ->
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