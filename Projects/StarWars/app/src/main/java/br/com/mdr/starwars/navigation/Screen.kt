package br.com.mdr.starwars.navigation

import br.com.mdr.starwars.R
import br.com.mdr.starwars.common.Constants.CHARACTER_NAME_KEY
import br.com.mdr.starwars.common.Constants.FILM_ID_KEY

sealed class Screen(val route: String) {
    data object Splash: Screen("splash_screen")
    data object Films: Screen("films")
    data object FilmDetail: Screen("film_detail/{$FILM_ID_KEY}") {
        fun passFilmId(filmId: Int): String = "film_detail/$filmId"
    }
    data object Characters: Screen("characters")
    data object CharacterDetail: Screen("character_detail/{$CHARACTER_NAME_KEY}") {
        fun passCharacterName(name: String): String = "character_detail/$name"
    }
}

sealed class BottomBarScreen(
    val route: String,
    val icon: Int,
    val label: Int
) {
    data object Categories: BottomBarScreen(
        route = "categories",
        icon = R.drawable.ic_categories,
        label = R.string.categories
    )
    data object Favorites: BottomBarScreen(
        route = "favorites",
        icon = R.drawable.ic_favorites,
        label = R.string.favorites
    )
    data object LastSeen: BottomBarScreen(
        route = "last_seen",
        icon = R.drawable.ic_last_seen,
        label = R.string.last_seen
    )
}