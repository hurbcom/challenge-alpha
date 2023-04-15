package br.com.hurbandroidchallenge.presentation.screens.character.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.data.mapper.characters.toModel
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
fun CharacterDetailMainScreen(
    navHostController: NavHostController,
    viewModel: CharacterDetailViewModel,
) {
    val characterUI = viewModel.characterUI.value
    Scaffold(
        topBar = {
            TopBar(
                title = characterUI.character?.name.orEmpty(),
                onBackPressed = { navHostController.navigateUp() },
                actions = {
                    TopBarIcon(
                        onClick = {
                            viewModel.favorite()
                        },
                        imageVector = if (characterUI.favorite) Icons.Default.Star else Icons.Default.StarBorder
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
            viewModel.characterState.collectAsState().value.let { response ->
                when (response) {
                    is StateUI.Error -> DefaultErrorScreen()
                    is StateUI.Idle -> Unit
                    is StateUI.Processed -> {
                        CharacterDetailScreen(
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
private fun CharacterDetailScreen(
    viewModel: CharacterDetailViewModel,
    navHostController: NavHostController,
) {
    val characterUI = viewModel.characterUI.value
    characterUI.character?.let { character ->
        val characterModel = character.toModel()
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            CategoryItemDetail(
                itemModel = characterModel.copy(
                    otherFields = characterModel.otherFields.plus(
                        "Home world" to characterUI.homeWorld?.name.orEmpty()
                    )
                )
            ) {
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
                }
            }
        }
    }
}