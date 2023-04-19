package br.com.vaniala.starwars.data.mapper

import br.com.vaniala.starwars.domain.model.Category
import br.com.vaniala.starwars.domain.model.CategoryResponse

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
fun CategoryResponse.toCategoryList(): List<Category> {
    return listOf(
        Category(name = "films", url = this.films, isUpdate = true),
        Category(name = "people", url = this.people, isUpdate = true),
        Category(name = "planets", url = this.planets, isUpdate = true),
        Category(name = "species", url = this.species, isUpdate = true),
        Category(name = "starships", url = this.starships, isUpdate = true),
        Category(name = "vehicles", url = this.vehicles, isUpdate = true),
    )
}
