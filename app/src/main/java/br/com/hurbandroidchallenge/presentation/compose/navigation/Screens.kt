package br.com.hurbandroidchallenge.presentation.compose.navigation

sealed class Screens(route: String, argumentKey: String) : ScreenNavOperations(route, argumentKey) {

    object Home : Screens(
        route = "home",
        argumentKey = "home_key"
    )

    object CategoryDetail : Screens(
        route = "category_detail",
        argumentKey = "category_detail_key"
    )
}