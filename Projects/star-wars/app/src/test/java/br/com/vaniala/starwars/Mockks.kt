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

val categoriesListMock = categoriesResultMock.toCategoryList()
