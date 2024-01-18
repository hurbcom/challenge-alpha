package com.vdemelo.starwarswiki.model

import com.vdemelo.starwarswiki.data.remote.response.PlanetResponse
import com.vdemelo.starwarswiki.utils.nonNullOrEmpty

class Planet(
    val climate: String?,
    val created: String?,
    val diameter: String?,
    val edited: String?,
    val films: List<String>,
    val gravity: String?,
    val name: String?,
    val orbitalPeriod: String?,
    val population: String?,
    val residents: List<String?>?,
    val rotationPeriod: String?,
    val surfaceWater: String?,
    val terrain: String?,
    val url: String?
) {

    constructor(response: PlanetResponse) : this(
        climate = response.climate,
        created = response.created,
        diameter = response.diameter,
        edited = response.edited,
        films = response.films.nonNullOrEmpty(),
        gravity = response.gravity,
        name = response.name,
        orbitalPeriod = response.orbitalPeriod,
        population = response.population,
        residents = response.residents.nonNullOrEmpty(),
        rotationPeriod = response.rotationPeriod,
        surfaceWater = response.surfaceWater,
        terrain = response.terrain,
        url = response.url
    )

}

fun List<PlanetResponse>.toModel(): List<Planet> = this.map { Planet(it) }
