package br.com.hurbandroidchallenge.data.mapper

import br.com.hurbandroidchallenge.commom.mapper.Mapper
import br.com.hurbandroidchallenge.data.remote.model.HomeListResponse
import br.com.hurbandroidchallenge.domain.model.HomeList

class HomeListResponseToEntityMapper : Mapper<HomeListResponse, HomeList> {
    override fun map(input: HomeListResponse) = input.run {
        HomeList(
            people = people.orEmpty(),
            films = films.orEmpty(),
            planets = planets.orEmpty(),
            species = species.orEmpty(),
            starships = starships.orEmpty(),
            vehicles = vehicles.orEmpty()
        )
    }
}