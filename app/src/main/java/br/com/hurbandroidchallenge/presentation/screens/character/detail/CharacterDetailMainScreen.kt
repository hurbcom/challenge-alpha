package br.com.hurbandroidchallenge.presentation.screens.character.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.commom.extension.toRoman
import br.com.hurbandroidchallenge.data.mapper.characters.toModel
import br.com.hurbandroidchallenge.presentation.compose.components.CategoryItemDetail
import br.com.hurbandroidchallenge.presentation.compose.components.OtherCategoryCard
import br.com.hurbandroidchallenge.presentation.compose.navigation.Screens
import br.com.hurbandroidchallenge.presentation.compose.widgets.image.SmallCategoryItemImage
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.error.DefaultErrorText
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.loading.DefaultLoading
import br.com.hurbandroidchallenge.presentation.compose.widgets.top_bar.TopBar
import br.com.hurbandroidchallenge.presentation.model.StateUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailMainScreen(
    navHostController: NavHostController,
    viewModel: CharacterDetailViewModel,
    url: String,
) {
    val characterUI = viewModel.characterUI.value
    LaunchedEffect(Unit) {
        viewModel.loadCharacter(url = url)
    }
    Scaffold(
        topBar = {
            TopBar(
                title = characterUI.character?.name.orEmpty(),
                onBackPressed = { navHostController.navigateUp() }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            characterUI.character?.let { character ->
                val characterModel = character.toModel()
                CategoryItemDetail(
                    itemModel = characterModel.copy(
                        fields = characterModel.fields.plus(
                            listOf(
                                "Ano de nascimento" to character.birthYear,
                                "Gênero" to character.gender,
                                "Cor do cabelo" to character.hairColor
                            )
                        )
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(all = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        OtherCategoryCard(name = "Filmes") {
                            viewModel.filmsState.collectAsState().value.let { response ->
                                when (response) {
                                    is StateUI.Error -> DefaultErrorText()
                                    is StateUI.Idle -> Unit
                                    is StateUI.Processed -> {
                                        LazyRow(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                                            contentPadding = PaddingValues(all = 16.dp)
                                        ) {
                                            items(characterUI.films) { film ->
                                                SmallCategoryItemImage(
                                                    text = "Episode ${film.episodeId.toRoman()}",
                                                    textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                                    image = film.image,
                                                    onClick = {
                                                        navHostController.navigate(Screens.FilmDetail.routeWithArgument(film.url))
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
}