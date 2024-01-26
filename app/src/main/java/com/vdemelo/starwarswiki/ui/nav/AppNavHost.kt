package com.vdemelo.starwarswiki.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vdemelo.starwarswiki.ui.screens.home.HomeScreen
import com.vdemelo.starwarswiki.ui.screens.planets.details.PlanetDetailsScreen
import com.vdemelo.starwarswiki.ui.screens.planets.list.PlanetListScreen
import com.vdemelo.starwarswiki.ui.screens.species.details.SpeciesDetailsScreen
import com.vdemelo.starwarswiki.ui.screens.species.list.SpeciesListScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavItem.Home.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = NavItem.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(route = NavItem.SpeciesList.route) {
            SpeciesListScreen(navController = navController)
        }

        composable(
            route = NavItem.SpeciesDetails.route,
            arguments = listOf(
                navArgument(NavigationArgs.ITEM_ID.name) { type = NavType.IntType }
            )
        ) {
            val speciesId = remember { it.arguments?.getInt(NavigationArgs.ITEM_ID.name) }
            SpeciesDetailsScreen(navController = navController, id = speciesId)
        }

        composable(route = NavItem.PlanetsList.route) {
            PlanetListScreen(navController = navController)
        }

        composable(
            route = NavItem.PlanetDetails.route,
            arguments = listOf(
                navArgument(NavigationArgs.ITEM_ID.name) { type = NavType.IntType }
            )
        ) {
            val planetId = remember { it.arguments?.getInt(NavigationArgs.ITEM_ID.name) }
            PlanetDetailsScreen(navController = navController, id = planetId)
        }
    }
}
