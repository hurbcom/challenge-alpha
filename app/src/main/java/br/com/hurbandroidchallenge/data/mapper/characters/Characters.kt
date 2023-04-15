package br.com.hurbandroidchallenge.data.mapper.characters

import androidx.compose.ui.layout.ContentScale
import br.com.hurbandroidchallenge.commom.extension.idFromUrl
import br.com.hurbandroidchallenge.commom.extension.ifNull
import br.com.hurbandroidchallenge.data.local.model.PeopleEntity
import br.com.hurbandroidchallenge.data.local.model.UpdateEntity
import br.com.hurbandroidchallenge.data.remote.config.ApiUrls
import br.com.hurbandroidchallenge.data.remote.model.PeopleDto
import br.com.hurbandroidchallenge.presentation.model.ItemModel
import br.com.hurbandroidchallenge.domain.model.People
import br.com.hurbandroidchallenge.presentation.model.SmallItemModel

fun PeopleEntity.toPeople() = this.run {
    val id = url.idFromUrl()
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
        url = url,
        image = image,
        favorite = favorite,
        lastSeen = lastSeen
    )
}

fun PeopleDto.toEntity() = this.run {
    val id = url?.idFromUrl()
    PeopleEntity(
        id = id ifNull 0,
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
        url = url.orEmpty(),
        image = "${ApiUrls.imageBaseUrl}characters/$id.jpg",
        lastSeen = null,
        favorite = false
    )
}

fun People.toSmallModel() = SmallItemModel(
    url = url,
    image = image,
    name = name
)

fun People.toModel() = ItemModel(
    url = url,
    image = image,
    firstFields = listOf(
        "Name" to name,
        "Height" to height,
        "Mass" to mass
    ),
    contentScale = ContentScale.FillWidth,
    aspectRatio = 4f / 5f,
    otherFields = listOf(
        "Gender" to gender,
        "Hair color" to hairColor,
        "Birth yeah" to birthYear,
        "Skin color" to skinColor
    )
)

fun People.toEntity() = UpdateEntity(
    id = id,
    lastSeen = lastSeen,
    favorite = favorite
)

val charactersEntity = listOf(
    PeopleEntity(
        name = "Luke Skywalker",
        height = "172",
        mass = "77",
        hairColor = "blond",
        skinColor = "fair",
        birthYear = "19BBY",
        gender = "male",
        homeWorld = "https://swapi.dev/api/planets/1/",
        films = listOf(
            "https://swapi.dev/api/films/1/",
            "https://swapi.dev/api/films/2/",
            "https://swapi.dev/api/films/3/",
            "https://swapi.dev/api/films/6/"
        ),
        vehicles = listOf(
            "https://swapi.dev/api/vehicles/14/",
            "https://swapi.dev/api/vehicles/30/"
        ),
        starships = listOf(
            "https://swapi.dev/api/starships/12/",
            "https://swapi.dev/api/starships/22/"
        ),
        url = "https://swapi.dev/api/people/1/",
        favorite = false,
        lastSeen = null,
        id = 99,
        image = "${ApiUrls.imageBaseUrl}characters/99.jpg",
    )
)