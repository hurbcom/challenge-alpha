package br.com.gstoduto.starwars.navigation.movie

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.gstoduto.starwars.model.Movie
import br.com.gstoduto.starwars.navigation.DestinationsStarWarsApp
import br.com.gstoduto.starwars.ui.screens.movie.MovieScreen
import br.com.gstoduto.starwars.ui.viewmodels.movie.MovieViewModel

fun NavGraphBuilder.movieScreen(
    onNavigateToMovieDetails: (Movie) -> Unit,
) {
    composable(DestinationsStarWarsApp.movieRoute.rota) {
        val viewModel = hiltViewModel<MovieViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        MovieScreen(
            uiState = uiState,
            columns = 2,
            onMovieClick = onNavigateToMovieDetails,
            onRetryLoadMovies = {
                viewModel.loadMovies()
            }
        )
    }
}

fun NavController.navigateToMovie(
    navOptions: NavOptions? = null
) {
    navigate(DestinationsStarWarsApp.movieRoute.rota, navOptions)
}