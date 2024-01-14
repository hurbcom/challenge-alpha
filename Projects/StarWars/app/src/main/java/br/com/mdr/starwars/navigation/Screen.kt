package br.com.mdr.starwars.navigation

sealed class Screen(val route: String) {
    data object Splash: Screen("splash_screen")
    data object Home: Screen("home")
}