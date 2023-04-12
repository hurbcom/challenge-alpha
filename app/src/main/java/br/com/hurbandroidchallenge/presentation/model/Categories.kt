package br.com.hurbandroidchallenge.presentation.model

import br.com.hurbandroidchallenge.R
import br.com.hurbandroidchallenge.data.remote.config.ApiUrls
import br.com.hurbandroidchallenge.presentation.compose.navigation.Screens

enum class Categories(val nameRes: Int, val url: String, val image: String, val route: String) {
    Characters(
        nameRes = R.string.home_categories_people,
        url = ApiUrls.characters,
        image = "${ApiUrls.imageBaseUrl}/categories/character.jpg",
        route = Screens.Characters.route
    ),
    Films(
        nameRes = R.string.home_categories_films,
        url = ApiUrls.films,
        image = "${ApiUrls.imageBaseUrl}/categories/films.jpg",
        route = Screens.Films.route
    ),
    Species(
        nameRes = R.string.home_categories_species,
        url = ApiUrls.species,
        image = "${ApiUrls.imageBaseUrl}/categories/species.jpg",
        route = Screens.Species.route
    ),
    Starships(
        nameRes = R.string.home_categories_starships,
        url = ApiUrls.starships,
        image = "${ApiUrls.imageBaseUrl}/categories/starships.jpg",
        route = Screens.Starships.route
    ),
    Vehicles(
        nameRes = R.string.home_categories_vehicles,
        url = ApiUrls.vehicles,
        image = "${ApiUrls.imageBaseUrl}/categories/vehicles.jpg",
        route = Screens.Vehicles.route
    ),
    Planets(
        nameRes = R.string.home_categories_planets,
        url = ApiUrls.planets,
        image = "${ApiUrls.imageBaseUrl}/categories/planets.jpg",
        route = Screens.Planets.route
    );
}