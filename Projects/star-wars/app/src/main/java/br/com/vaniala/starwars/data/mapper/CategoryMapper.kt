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
        Category(name = "films", url = this.films),
        Category(name = "people", url = this.people),
        Category(name = "planets", url = this.planets),
        Category(name = "species", url = this.species),
        Category(name = "starships", url = this.starships),
        Category(name = "vehicles", url = this.vehicles),
    )
}
