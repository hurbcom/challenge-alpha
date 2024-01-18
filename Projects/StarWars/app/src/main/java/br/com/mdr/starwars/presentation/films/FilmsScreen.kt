package br.com.mdr.starwars.presentation.films

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.mdr.starwars.domain.model.Film
import br.com.mdr.starwars.domain.model.PageState
import br.com.mdr.starwars.navigation.Screen
import br.com.mdr.starwars.presentation.common.EmptyScreen
import br.com.mdr.starwars.presentation.components.SearchTopBar
import br.com.mdr.starwars.presentation.components.ShimmerEffect
import br.com.mdr.starwars.ui.theme.Dimens.MEDIUM_PADDING
import br.com.mdr.starwars.ui.theme.Dimens.TOP_APP_BAR_PADDING
import org.koin.androidx.compose.koinViewModel

@Composable
fun FilmsScreen(navController: NavHostController) {
    val viewModel: FilmsViewModel = koinViewModel()
    val films = viewModel.films.collectAsLazyPagingItems()
    val searchQuery by viewModel.searchQuery

    LaunchedEffect(key1 = null, block = {
        viewModel.getFilms(searchQuery)
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HandlePagingResult(
            films = films,
            navController = navController,
            viewModel = viewModel
        )
        SearchTopBar(
            text = searchQuery,
            onTextChange = { viewModel.updateSearchQuery(it) },
            onSearchClicked = {
                viewModel.getFilms(query = it)
            },
            onCloseClicked = {
                navController.popBackStack()
            }
        )
    }
}

@Composable
fun HandlePagingResult(
    films: LazyPagingItems<Film>,
    navController: NavHostController,
    viewModel: FilmsViewModel
) {
    films.apply {
        val error = when {
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            else -> null
        }

        when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
            }
            error != null -> {
                EmptyScreen(error = PageState.Error(error.error), viewModel = viewModel)
            }
            this.itemCount < 1 -> {
                EmptyScreen(viewModel = viewModel)
            }
            else -> FilmsList(
                films = films,
                navController = navController
            )
        }
    }
}

@Composable
fun FilmsList(
    films: LazyPagingItems<Film>,
    navController: NavHostController
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            bottom = MEDIUM_PADDING,
            end = MEDIUM_PADDING,
            start = MEDIUM_PADDING,
            top = TOP_APP_BAR_PADDING
        ),
        horizontalArrangement = Arrangement.spacedBy(MEDIUM_PADDING),
        verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
    ) {
        items(count = films.itemCount) {index ->
            films[index]?.let { film ->
                FilmItem(film = film) { filmId ->
                    navController.navigate(Screen.FilmDetail.passFilmId(filmId))
                }
            }
        }
    }
}