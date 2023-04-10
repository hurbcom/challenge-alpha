package br.com.vaniala.starwars.data.mapper

import br.com.vaniala.starwars.domain.model.Category
import br.com.vaniala.starwars.domain.model.CategoryResult

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
fun CategoryResult.toCategoryList(): List<Category> {
    return listOf(
        Category("films", this.films),
        Category("people", this.people),
        Category("planets", this.planets),
        Category("species", this.species),
        Category("starships", this.starships),
        Category("vehicles", this.vehicles),
    )
}
