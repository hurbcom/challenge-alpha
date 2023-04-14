package br.com.hurbandroidchallenge.data.mapper.characters

import androidx.compose.ui.layout.ContentScale
import br.com.hurbandroidchallenge.commom.extension.idFromUrl
import br.com.hurbandroidchallenge.data.local.model.PeopleEntity
import br.com.hurbandroidchallenge.data.remote.config.ApiUrls
import br.com.hurbandroidchallenge.data.remote.model.PeopleDto
import br.com.hurbandroidchallenge.domain.model.ItemModel
import br.com.hurbandroidchallenge.domain.model.People

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
        image = image
    )
}

fun PeopleDto.toEntity() = this.run {
    val id = url?.idFromUrl()
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
        url = url.orEmpty(),
        image = "${ApiUrls.imageBaseUrl}characters/$id.jpg"
    )
}


fun PeopleDto.toPeople() = this.run {
    val id = url?.idFromUrl()
    People(
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
        url = url.orEmpty(),
        image = "${ApiUrls.imageBaseUrl}characters/$id.jpg"
    )
}

fun People.toModel() = ItemModel(
    url = url,
    image = image,
    fields = listOf(
        "Name" to name,
        "Height" to height,
        "Mass" to mass
    ),
    contentScale = ContentScale.FillWidth,
    aspectRatio = 4f / 5f,
)