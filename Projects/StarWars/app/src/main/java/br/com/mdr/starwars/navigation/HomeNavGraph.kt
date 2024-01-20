package br.com.mdr.starwars.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import br.com.mdr.starwars.common.Constants.CHARACTER_NAME_KEY
import br.com.mdr.starwars.common.Constants.FILM_ID_KEY
import br.com.mdr.starwars.ui.presentation.categories.CategoriesScreen
import br.com.mdr.starwars.ui.presentation.characters.CharactersScreen
import br.com.mdr.starwars.ui.presentation.characters.detail.CharacterDetailScreen
import br.com.mdr.starwars.ui.presentation.components.SpaceBackgroundView
import br.com.mdr.starwars.ui.presentation.favorites.FavoritesScreen
import br.com.mdr.starwars.ui.presentation.films.FilmsScreen
import br.com.mdr.starwars.ui.presentation.films.detail.FilmDetailScreen
import br.com.mdr.starwars.ui.presentation.lastSeen.LastSeenScreen

@Composable
fun HomeNavGraph(modifier: Modifier, navController: NavHostController) {
    SpaceBackgroundView {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = BottomBarScreen.Categories.route
        ) {
            composable(route = BottomBarScreen.Categories.route) {
                CategoriesScreen(navController)
            }
            composable(route = BottomBarScreen.Favorites.route) {
                FavoritesScreen(navController)
            }
            composable(route = BottomBarScreen.LastSeen.route) {
                LastSeenScreen(navController)
            }
            categoriesDetailsNavGraph(navController)
        }
    }
}

fun NavGraphBuilder.categoriesDetailsNavGraph(navController: NavHostController) {
    navigation(
        route = GraphRoute.CATEGORIES,
        startDestination = Screen.Films.route
    ) {
        composable(route = Screen.Films.route) {
            FilmsScreen(navController = navController)
        }
        composable(
            route = Screen.FilmDetail.route,
            arguments = listOf(
                navArgument(name = FILM_ID_KEY) {
                    type = NavType.IntType
                }
            )
        ) {
            FilmDetailScreen(navController = navController)
        }
        composable(route = Screen.Characters.route) {
            CharactersScreen(navController = navController)
        }
        composable(
            route = Screen.CharacterDetail.route,
            arguments = listOf(
                navArgument(name = CHARACTER_NAME_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            CharacterDetailScreen(navController = navController)
        }
    }
}