package com.vdemelo.starwarswiki.ui.nav

enum class Screen {
    HOME,
    SPECIES_LIST,
    SPECIES_DETAILS,
    PLANETS_LIST,
    PLANET_DETAILS
}

sealed class NavItem(val route: String) {

    object Home : NavItem(route = Screen.HOME.name)

    object SpeciesList : NavItem(route = Screen.SPECIES_LIST.name)

    object SpeciesDetails : NavItem(
        route = buildComposableRouteMap(
            basePath = Screen.SPECIES_DETAILS.name,
            argumentNames = listOf(
                NavigationArgs.ITEM_ID.name
            )
        )
    )

    object PlanetsList : NavItem(route = Screen.PLANETS_LIST.name)

    object PlanetDetails : NavItem(
        route = buildComposableRouteMap(
            basePath = Screen.PLANET_DETAILS.name,
            argumentNames = listOf(
                NavigationArgs.ITEM_ID.name
            )
        )
    )
}
