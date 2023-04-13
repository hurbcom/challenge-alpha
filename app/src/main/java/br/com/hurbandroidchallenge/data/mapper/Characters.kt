package br.com.hurbandroidchallenge.data.mapper

import br.com.hurbandroidchallenge.data.local.model.PeopleEntity
import br.com.hurbandroidchallenge.data.remote.config.ApiUrls
import br.com.hurbandroidchallenge.data.remote.model.PeopleDto
import br.com.hurbandroidchallenge.domain.model.ItemModel
import br.com.hurbandroidchallenge.domain.model.People

fun PeopleEntity.toPeople() = this.run {
    val id = url.split("/").last { it.isNotBlank() }.toInt()
    People(
        id = id,
        name = name,
        height = height,
        birthYear = birthYear,
        gender = gender,
        hairColor = hairColor,
        mass = mass,
        homeWorld = homeWorld,
        skinColor = skinColor,
        vehicles = vehicles,
        starships = starships,
        films = films,
        url = url
    )
}

fun PeopleDto.toEntity() = this.run {
    val id = url?.split("/")?.last { it.isNotBlank() }?.toInt()
    PeopleEntity(
        id = id ?: 0,
        name = name.orEmpty(),
        height = height.orEmpty(),
        birthYear = birthYear.orEmpty(),
        gender = gender.orEmpty(),
        hairColor = hairColor.orEmpty(),
        mass = mass.orEmpty(),
        homeWorld = homeWorld.orEmpty(),
        skinColor = skinColor.orEmpty(),
        vehicles = vehicles.orEmpty(),
        starships = starships.orEmpty(),
        films = films.orEmpty(),
        url = url.orEmpty()
    )
}

fun People.toModel() = ItemModel(
    image = "${ApiUrls.imageBaseUrl}characters/$id.jpg",
    fields = listOf(
        "Nome" to name,
        "Altura" to height,
        "Massa" to mass
    )
)