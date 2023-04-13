package br.com.hurbandroidchallenge.presentation.compose.navigation

sealed class Screens(route: String, argumentKey: String) : ScreenNavOperations(route, argumentKey) {

    object Home : Screens(
        route = "home",
        argumentKey = "home_key"
    )

    object Characters : Screens(
        route = "characters",
        argumentKey = "characters_key"
    )

    object CharacterDetail : Screens(
        route = "character_detail?character_detail_key={character_detail_key}",
        argumentKey = "character_detail_key"
    )

    object Films : Screens(
        route = "films",
        argumentKey = "films_key"
    )

    object Species : Screens(
        route = "species",
        argumentKey = "species_key"
    )

    object Starships : Screens(
        route = "starships",
        argumentKey = "starships_key"
    )

    object Vehicles : Screens(
        route = "vehicles",
        argumentKey = "vehicles"
    )

    object Planets : Screens(
        route = "planets",
        argumentKey = "planets"
    )
}