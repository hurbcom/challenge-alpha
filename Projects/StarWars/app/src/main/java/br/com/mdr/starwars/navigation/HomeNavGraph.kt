package br.com.mdr.starwars.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.mdr.starwars.presentation.categories.CategoriesScreen
import br.com.mdr.starwars.presentation.favorites.FavoritesScreen
import br.com.mdr.starwars.presentation.lastSeen.LastSeenScreen

@Composable
fun HomeNavGraph(modifier: Modifier, navController: NavHostController) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = BottomBarScreen.Categories.route
    ) {
        composable(route = BottomBarScreen.Categories.route) {
            CategoriesScreen(navController)
        }
        composable(route = BottomBarScreen.Favorites.route) {
            FavoritesScreen()
        }
        composable(route = BottomBarScreen.LastSeen.route) {
            LastSeenScreen()
        }
    }
}