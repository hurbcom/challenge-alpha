package com.vdemelo.starwarswiki.ui.nav

private const val PATH_SEPARATOR = "/"

fun buildComposableRouteMap(
    basePath: String,
    argumentNames: List<String> = listOf()
): String {
    val formattedArgumentNames = argumentNames.map { "{${it}}" }
    val pieces: List<String> = listOf(basePath) + formattedArgumentNames
    return pieces.joinToString(separator = PATH_SEPARATOR)
}

fun buildSpeciesDetailsRoute(number: String): String {
    return listOf<String>(
        Screen.SPECIES_DETAILS.name,
        number
    ).joinToString(separator = PATH_SEPARATOR)
}
