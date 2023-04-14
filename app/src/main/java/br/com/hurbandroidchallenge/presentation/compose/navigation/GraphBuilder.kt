package br.com.hurbandroidchallenge.presentation.compose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import br.com.hurbandroidchallenge.presentation.screens.character.detail.CharacterDetailMainScreen
import br.com.hurbandroidchallenge.presentation.screens.character.detail.CharacterDetailViewModel
import br.com.hurbandroidchallenge.presentation.screens.character.list.CategoryDetailMainScreen
import br.com.hurbandroidchallenge.presentation.screens.character.list.CharactersViewModel
import br.com.hurbandroidchallenge.presentation.screens.film.detail.FilmDetailMainScreen
import br.com.hurbandroidchallenge.presentation.screens.film.detail.FilmDetailViewModel
import br.com.hurbandroidchallenge.presentation.screens.film.list.FilmsMainScreen
import br.com.hurbandroidchallenge.presentation.screens.film.list.FilmsViewModel
import br.com.hurbandroidchallenge.presentation.screens.home.HomeMainScreen
import br.com.hurbandroidchallenge.presentation.screens.planet.detail.PlanetDetailMainScreen
import br.com.hurbandroidchallenge.presentation.screens.planet.detail.PlanetDetailViewModel
import br.com.hurbandroidchallenge.presentation.screens.planet.list.PlanetsMainScreen
import br.com.hurbandroidchallenge.presentation.screens.planet.list.PlanetsViewModel
import com.google.accompanist.navigation.animation.composable
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.home(
    navHostController: NavHostController,
) {
    composable(
        route = Screens.Home.route
    ) {
        HomeMainScreen(
            navHostController = navHostController
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
fun NavGraphBuilder.characterDetail(
    navHostController: NavHostController,
) {
    composable(
        route = Screens.CharacterDetail.route,
        arguments = listOf(navArgument(name = Screens.CharacterDetail.argumentKey) {
            type = NavType.StringType
        })
    ) {
        val url = it.arguments?.getString(Screens.CharacterDetail.argumentKey).orEmpty()
        val viewModel = getViewModel<CharacterDetailViewModel>(
            parameters = { parametersOf(url) }
        )
        CharacterDetailMainScreen(
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


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.filmDetail(
    navHostController: NavHostController,
) {
    composable(
        route = Screens.FilmDetail.route,
        arguments = listOf(navArgument(name = Screens.FilmDetail.argumentKey) {
            type = NavType.StringType
        })
    ) {
        val url = it.arguments?.getString(Screens.FilmDetail.argumentKey).orEmpty()
        val viewModel = getViewModel<FilmDetailViewModel>(
            parameters = { parametersOf(url) }
        )
        FilmDetailMainScreen(
            navHostController = navHostController,
            viewModel = viewModel
        )
    }
}



@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.planets(
    navHostController: NavHostController,
) {
    composable(
        route = Screens.Planets.route
    ) {
        val viewModel = getViewModel<PlanetsViewModel>()
        PlanetsMainScreen(
            navHostController = navHostController,
            viewModel = viewModel
        )
    }
}


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.planetDetail(
    navHostController: NavHostController,
) {
    composable(
        route = Screens.PlanetDetail.route,
        arguments = listOf(navArgument(name = Screens.PlanetDetail.argumentKey) {
            type = NavType.StringType
        })
    ) {
        val url = it.arguments?.getString(Screens.PlanetDetail.argumentKey).orEmpty()
        val viewModel = getViewModel<PlanetDetailViewModel>(
            parameters = { parametersOf(url) }
        )
        PlanetDetailMainScreen(
            navHostController = navHostController,
            viewModel = viewModel
        )
    }
}
