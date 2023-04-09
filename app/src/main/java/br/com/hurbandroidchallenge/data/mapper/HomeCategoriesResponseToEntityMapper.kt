package br.com.hurbandroidchallenge.data.mapper

import br.com.hurbandroidchallenge.commom.mapper.Mapper
import br.com.hurbandroidchallenge.data.remote.model.HomeCategoriesResponse
import br.com.hurbandroidchallenge.domain.model.HomeCategories

class HomeCategoriesResponseToEntityMapper : Mapper<HomeCategoriesResponse, HomeCategories> {
    override fun map(input: HomeCategoriesResponse) = input.run {
        HomeCategories(
            people = people.orEmpty(),
            films = films.orEmpty(),
            planets = planets.orEmpty(),
            species = species.orEmpty(),
            starships = starships.orEmpty(),
            vehicles = vehicles.orEmpty()
        )
    }
}