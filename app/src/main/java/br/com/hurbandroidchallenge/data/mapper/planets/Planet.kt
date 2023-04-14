package br.com.hurbandroidchallenge.data.mapper.films

import androidx.compose.ui.layout.ContentScale
import br.com.hurbandroidchallenge.commom.extension.idFromUrl
import br.com.hurbandroidchallenge.commom.extension.ifNull
import br.com.hurbandroidchallenge.data.local.model.PlanetEntity
import br.com.hurbandroidchallenge.data.remote.config.ApiUrls
import br.com.hurbandroidchallenge.data.remote.model.PlanetDto
import br.com.hurbandroidchallenge.domain.model.ItemModel
import br.com.hurbandroidchallenge.domain.model.Planet

fun PlanetDto.toPlanet() = this.run {
    val id = url.idFromUrl() ifNull 0
    Planet(
        id = id,
        name = name,
        films = films,
        url = url,
        climate = climate,
        diameter = diameter,
        gravity = gravity,
        orbitalPeriod = orbitalPeriod,
        population = population,
        residents = residents,
        rotationPeriod = rotationPeriod,
        surfaceWater = surfaceWater,
        terrain = terrain,
        image = "${ApiUrls.imageBaseUrl}planets/${id}.jpg"
    )
}

fun PlanetDto.toEntity() = this.run {
    val id = url.idFromUrl() ifNull 0
    PlanetEntity(
        id = id,
        name = name,
        films = films,
        url = url,
        climate = climate,
        diameter = diameter,
        gravity = gravity,
        orbitalPeriod = orbitalPeriod,
        population = population,
        residents = residents,
        rotationPeriod = rotationPeriod,
        surfaceWater = surfaceWater,
        terrain = terrain,
        image = "${ApiUrls.imageBaseUrl}planets/${id}.jpg"
    )
}

fun PlanetEntity.toPlanet() = Planet(
    id = id,
    name = name,
    films = films,
    url = url,
    climate = climate,
    diameter = diameter,
    gravity = gravity,
    orbitalPeriod = orbitalPeriod,
    population = population,
    residents = residents,
    rotationPeriod = rotationPeriod,
    surfaceWater = surfaceWater,
    terrain = terrain,
    image = image
)

fun Planet.toModel() = ItemModel(
    url = url,
    image = image,
    contentScale = ContentScale.Crop,
    aspectRatio = 1f,
    fields = listOf(
        "Population" to population,
        "Rotation period" to rotationPeriod,
        "Orbital Period" to orbitalPeriod
    )
)