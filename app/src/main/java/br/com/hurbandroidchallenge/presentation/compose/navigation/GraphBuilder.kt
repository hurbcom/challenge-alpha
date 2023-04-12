package br.com.hurbandroidchallenge.presentation.compose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.presentation.screens.characters.CategoryDetailMainScreen
import br.com.hurbandroidchallenge.presentation.screens.characters.CharactersViewModel
import br.com.hurbandroidchallenge.presentation.screens.films.FilmsMainScreen
import br.com.hurbandroidchallenge.presentation.screens.films.FilmsViewModel
import br.com.hurbandroidchallenge.presentation.screens.home.HomeListViewModel
import br.com.hurbandroidchallenge.presentation.screens.home.HomeMainScreen
import com.google.accompanist.navigation.animation.composable
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.home(
    navHostController: NavHostController,
) {
    composable(
        route = Screens.Home.route
    ) {
        val viewModel = getViewModel<HomeListViewModel>()
        HomeMainScreen(
            navHostController = navHostController,
            viewModel = viewModel
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.characters(
    navHostController: NavHostController,
) {
    composable(
        route = Screens.Characters.route
    ) {
        val viewModel = getViewModel<CharactersViewModel>()
        CategoryDetailMainScreen(
            navHostController = navHostController,
            viewModel = viewModel
        )
    }
}


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.films(
    navHostController: NavHostController,
) {
    composable(
        route = Screens.Films.route
    ) {
        val viewModel = getViewModel<FilmsViewModel>()
        FilmsMainScreen(
            navHostController = navHostController,
            viewModel = viewModel
        )
    }
}
