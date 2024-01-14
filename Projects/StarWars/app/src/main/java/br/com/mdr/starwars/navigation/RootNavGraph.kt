package br.com.mdr.starwars.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.mdr.starwars.presentation.home.HomeScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = GraphRoute.ROOT,
        startDestination = GraphRoute.SPLASH) {
        splashNavGraph(navController = navController)
        composable(route = GraphRoute.HOME) {
            HomeScreen()
        }
    }
}

object GraphRoute {
    const val ROOT = "root_graph"
    const val SPLASH = "splash_graph"
    const val HOME = "home_graph"
}