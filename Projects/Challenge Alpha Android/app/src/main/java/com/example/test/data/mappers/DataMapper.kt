package com.example.test.data.mappers

import com.example.test.data.datasources.network.models.PersonResponse
import com.example.test.data.datasources.network.models.PlanetResponse
import com.example.test.data.datasources.network.models.StarshipResponse
import com.example.test.domain.models.*
import com.example.test.utils.ImageHelper

object DataMapper {
    fun PersonResponse.map(): Person = Person(
        name = name.orEmpty(),
        height = height.orEmpty(),
        mass = mass.orEmpty(),
        gender = gender.orEmpty(),
        eyeColor = eyeColor.orEmpty(),
        image = ImageHelper.getPeopleImage(url.orEmpty())
    )

    fun PlanetResponse.map(): Planet = Planet(
        climate = climate.orEmpty(),
        diameter = diameter.orEmpty(),
        name = name.orEmpty(),
        population = population.orEmpty(),
        terrain = terrain.orEmpty(),
        image = ImageHelper.getPlanetsImage(url.orEmpty())
    )

    fun StarshipResponse.map(): Starship = Starship(
        cargoCapacity = cargoCapacity.orEmpty(),
        manufacturer = manufacturer.orEmpty(),
        name = name.orEmpty(),
        passengers = passengers.orEmpty(),
        starshipClass = starshipClass.orEmpty(),
        image = ImageHelper.getStarshipsImage(url.orEmpty())
    )
}