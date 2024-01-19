package br.com.mdr.starwars.ui.presentation.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import br.com.mdr.starwars.R
import br.com.mdr.starwars.domain.model.Favorite
import br.com.mdr.starwars.ui.presentation.components.BaseGridItem
import br.com.mdr.starwars.ui.presentation.components.HandlePageStateResult
import br.com.mdr.starwars.ui.theme.Dimens.MEDIUM_PADDING
import br.com.mdr.starwars.utils.isEven
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoritesScreen(navController: NavHostController) {
    val viewModel: FavoritesViewModel = koinViewModel()
    val pageState = viewModel.favorites.collectAsState()

    HandlePageStateResult(
        pageState = pageState.value,
        viewModel = viewModel
    ) {
        ListContent(
            favorite = it.result as? Favorite,
            navController = navController
        )
    }
}

@Composable
fun ListContent(favorite: Favorite?, navController: NavHostController) {
    favorite?.apply {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                bottom = MEDIUM_PADDING,
                end = MEDIUM_PADDING,
                start = MEDIUM_PADDING,
                top = MEDIUM_PADDING
            ),
            horizontalArrangement = Arrangement.spacedBy(MEDIUM_PADDING),
            verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
        ) {
            item(
                span = {
                    GridItemSpan(maxCurrentLineSpan)
                }
            ) {
                HeaderItem(textId = R.string.films)
            }
            items(count = favorite.films.size) {index ->
                favorite.films[index].apply {
                    BaseGridItem(
                        description = title,
                        url = filmUrl) {

                    }
                }
            }
            //Verifica se o número de favoritos em filmes é ímpar
            //Se for, é necessário adicionar uma coluna em branco
            if (favorite.films.size.isEven().not()) {
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
            items(count = favorite.characters.size) {index ->
                favorite.characters[index].apply {
                    BaseGridItem(
                        description = name,
                        url = characterUrl
                    ) {

                    }
                }
            }
        }
    }
}

@Composable
fun HeaderItem(textId: Int) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        text = stringResource(id = textId),
        style = MaterialTheme.typography.titleLarge
    )
}