package com.vdemelo.starwarswiki.ui.nav

enum class Screen {
    HOME,
    SPECIES_LIST,
    SPECIES_DETAILS,
    PLANETS_LIST,
    PLANET_DETAILS
}

sealed class NavigationItem(val route: String) {

    object Home : NavigationItem(route = Screen.HOME.name)

    object SpeciesList : NavigationItem(route = Screen.SPECIES_LIST.name)

    object SpeciesDetails : NavigationItem(
        route = buildComposableRouteMap(
            basePath = Screen.SPECIES_DETAILS.name,
            argumentNames = listOf(
                NavigationArgs.ITEM_ID.name
            )
        )
    )

    object PlanetsList : NavigationItem(route = Screen.PLANETS_LIST.name)

    object PlanetDetails : NavigationItem(
        route = buildComposableRouteMap(
            basePath = Screen.PLANET_DETAILS.name,
            argumentNames = listOf(
                NavigationArgs.ITEM_ID.name
            )
        )
    )
}
