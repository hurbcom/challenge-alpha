package br.com.mdr.starwars.ui.presentation.lastSeen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import br.com.mdr.starwars.R
import br.com.mdr.starwars.navigation.Screen
import br.com.mdr.starwars.ui.presentation.components.BaseGridItem
import br.com.mdr.starwars.ui.presentation.components.HandlePageStateResult
import br.com.mdr.starwars.ui.presentation.favorites.HeaderItem
import br.com.mdr.starwars.ui.theme.Dimens
import br.com.mdr.starwars.utils.isEven
import org.koin.androidx.compose.koinViewModel

@Composable
fun LastSeenScreen(navController: NavHostController) {
    val viewModel: LastSeenViewModel = koinViewModel()
    val pageState = viewModel.lastSeen.collectAsState()

    HandlePageStateResult(
        pageState = pageState.value,
        viewModel = viewModel
    ) {
        ListContent(
            viewModel = viewModel,
            navController = navController
        )
    }
}

@Composable
private fun ListContent(viewModel: LastSeenViewModel, navController: NavHostController) {
    val films = viewModel.films
    val characters = viewModel.characters

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            bottom = Dimens.MEDIUM_PADDING,
            end = Dimens.MEDIUM_PADDING,
            start = Dimens.MEDIUM_PADDING,
            top = Dimens.MEDIUM_PADDING
        ),
        horizontalArrangement = Arrangement.spacedBy(Dimens.MEDIUM_PADDING),
        verticalArrangement = Arrangement.spacedBy(Dimens.MEDIUM_PADDING)
    ) {
        item(
            span = {
                GridItemSpan(maxCurrentLineSpan)
            }
        ) {
            HeaderItem(textId = R.string.films)
        }
        items(count = films.size) { index ->
            films[index].apply {
                BaseGridItem(
                    description = title,
                    url = filmUrl
                ) {
                    navController.navigate(Screen.FilmDetail.passFilmId(this.id))
                }
            }
        }
        // Verifica se o número de favoritos em filmes é ímpar
        // Se for, é necessário adicionar uma coluna em branco
        if (films.size.isEven().not()) {
            item(
                span = {
                    GridItemSpan(maxCurrentLineSpan)
                }
            ) {
                Spacer(modifier = Modifier.fillMaxWidth())
            }
        }

        item(
            span = {
                GridItemSpan(maxCurrentLineSpan)
            }
        ) {
            HeaderItem(textId = R.string.characters)
        }
        items(count = characters.size) { index ->
            characters[index].apply {
                BaseGridItem(
                    description = name,
                    url = characterUrl
                ) {
                    navController.navigate(Screen.CharacterDetail.passCharacterName(name))
                }
            }
        }
    }
}
