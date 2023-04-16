package br.com.hurbandroidchallenge.presentation.screens.planet.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.data.mapper.planets.toModel
import br.com.hurbandroidchallenge.presentation.compose.components.item_model.CategoryItemsExpandableList
import br.com.hurbandroidchallenge.presentation.compose.components.item_model.CategoryItemDetail
import br.com.hurbandroidchallenge.presentation.compose.navigation.Screens
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.error.DefaultErrorScreen
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.loading.DefaultLoadingScreen
import br.com.hurbandroidchallenge.presentation.compose.widgets.top_bar.TopBar
import br.com.hurbandroidchallenge.presentation.compose.widgets.top_bar.TopBarIcon
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
                onBackPressed = { navHostController.navigateUp() },
                actions = {
                    TopBarIcon(
                        onClick = {
                            viewModel.favorite()
                        },
                        imageVector = if (planetUI.planet?.favorite == true) Icons.Default.Star else Icons.Default.StarBorder
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
                    CategoryItemsExpandableList(
                        name = "Movies",
                        listState = viewModel.filmsState.collectAsState().value,
                        onClick = { url ->
                            navHostController.navigate(
                                Screens.FilmDetail.routeWithArgument(url)
                            )
                        }
                    )
                    CategoryItemsExpandableList(
                        name = "Characters",
                        listState = viewModel.charactersState.collectAsState().value,
                        onClick = { url ->
                            navHostController.navigate(
                                Screens.CharacterDetail.routeWithArgument(url)
                            )
                        }
                    )
                }
            }
        }
    }

}