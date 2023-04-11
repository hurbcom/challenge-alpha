package br.com.hurbandroidchallenge.data.mapper

import br.com.hurbandroidchallenge.commom.mapper.Mapper
import br.com.hurbandroidchallenge.data.remote.model.PeopleResponse
import br.com.hurbandroidchallenge.domain.model.People

class PeopleResponseToEntityMapper : Mapper<PeopleResponse, People> {
    override fun map(input: PeopleResponse) = input.run {
        val id = url?.split("/")?.last { it.isNotBlank() }?.toInt()
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
            url = url.orEmpty()
        )
    }
}