package com.vdemelo.starwarswiki.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vdemelo.starwarswiki.R
import com.vdemelo.starwarswiki.ui.screens.species.details.SpeciesDetailsScreen
import com.vdemelo.starwarswiki.ui.screens.species.list.SpeciesListScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Home.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = NavigationItem.SpeciesList.route) {
            SpeciesListScreen(navController = navController)
        }

        composable(
            route = NavigationItem.SpeciesDetails.route,
            arguments = listOf(
                navArgument(NavigationArgs.SPECIES_NUMBER.name) {
                    type = NavType.IntType
                }
            )
        ) {
            val speciesNumber = remember {
                it.arguments?.getInt(NavigationArgs.SPECIES_NUMBER.name)
            }
            SpeciesDetailsScreen(
                navController = navController,
                number = speciesNumber
            )
        }
    }
}
