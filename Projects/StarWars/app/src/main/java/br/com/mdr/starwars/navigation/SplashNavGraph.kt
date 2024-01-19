package br.com.mdr.starwars.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.mdr.starwars.ui.presentation.splash.SplashScreen

fun NavGraphBuilder.splashNavGraph(navController: NavHostController) {
    navigation(
        route = GraphRoute.SPLASH,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen {
                navController.popBackStack()
                navController.navigate(route = GraphRoute.HOME)
            }
        }
    }
}