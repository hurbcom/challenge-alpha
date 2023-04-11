package br.com.hurbandroidchallenge.presentation.compose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.presentation.screens.categoryDetail.CategoryDetailMainScreen
import br.com.hurbandroidchallenge.presentation.screens.categoryDetail.CharactersViewModel
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
fun NavGraphBuilder.categoryDetail(
    navHostController: NavHostController,
) {
    composable(
        route = Screens.CategoryDetail.route
    ) {
        val viewModel = getViewModel<CharactersViewModel>()
        CategoryDetailMainScreen(
            navHostController = navHostController,
            viewModel = viewModel
        )
    }
}
