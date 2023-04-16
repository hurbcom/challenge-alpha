package br.com.hurbandroidchallenge.data.mapper.planets

import androidx.compose.ui.layout.ContentScale
import br.com.hurbandroidchallenge.commom.extension.idFromUrl
import br.com.hurbandroidchallenge.commom.extension.ifNull
import br.com.hurbandroidchallenge.data.local.model.PlanetEntity
import br.com.hurbandroidchallenge.data.local.model.UpdateEntity
import br.com.hurbandroidchallenge.data.remote.config.ApiUrls
import br.com.hurbandroidchallenge.data.remote.model.PlanetDto
import br.com.hurbandroidchallenge.domain.model.Planet
import br.com.hurbandroidchallenge.presentation.model.ItemModel
import br.com.hurbandroidchallenge.presentation.model.SmallItemModel

fun PlanetDto.toEntity() = this.run {
    val id = url?.idFromUrl() ifNull 0
    PlanetEntity(
        id = id,
        name = name.orEmpty(),
        films = films.orEmpty(),
        url = url.orEmpty(),
        climate = climate.orEmpty(),
        diameter = diameter.orEmpty(),
        gravity = gravity.orEmpty(),
        orbitalPeriod = orbitalPeriod.orEmpty(),
        population = population.orEmpty(),
        residents = residents.orEmpty(),
        rotationPeriod = rotationPeriod.orEmpty(),
        surfaceWater = surfaceWater.orEmpty(),
        terrain = terrain.orEmpty(),
        image = "${ApiUrls.imageBaseUrl}planets/${id}.jpg",
        lastSeen = null,
        favorite = false
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
    image = image,
    lastSeen = lastSeen,
    favorite = favorite
)

fun Planet.toEntity() = UpdateEntity(
    id = id,
    favorite = favorite,
    lastSeen = lastSeen
)

fun Planet.toSmallModel() = SmallItemModel(
    url = url,
    image = image,
    name = name
)

fun Planet.toModel() = ItemModel(
    url = url,
    image = image,
    contentScale = ContentScale.Crop,
    aspectRatio = 1f,
    firstFields = listOf(
        "Name" to name,
        "Population" to population,
        "Rotation period" to rotationPeriod
    ),
    otherFields = listOf(
        "Climate" to climate,
        "Gravity" to gravity,
        "Orbital Period" to orbitalPeriod
    )
)