package br.com.vaniala.starwars

import br.com.vaniala.starwars.data.mapper.toCategoryList
import br.com.vaniala.starwars.domain.model.CategoryResult

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
val categoriesResultMock = CategoryResult(
    films = "",
    people = "",
    planets = "",
    species = "",
    vehicles = "",
    starships = "",
)

val categoriesResultPopulateMock = CategoryResult(
    films = "https://swapi.dev/api/people/",
    people = "https://swapi.dev/api/planets/",
    planets = "https://swapi.dev/api/films/",
    species = "https://swapi.dev/api/species/",
    vehicles = "https://swapi.dev/api/vehicles/",
    starships = "https://swapi.dev/api/starships/",
)

val categoriesListMock = categoriesResultMock.toCategoryList()