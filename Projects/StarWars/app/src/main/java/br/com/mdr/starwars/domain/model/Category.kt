package br.com.mdr.starwars.domain.model

import br.com.mdr.starwars.common.Constants.CATEGORIES_URL
import br.com.mdr.starwars.common.Constants.IMAGE_BASE_URL
import br.com.mdr.starwars.common.Constants.IMAGE_EXTENSION

private const val CHARACTER = "character"
private const val PEOPLE = "people"

data class Category(
    val name: String,
    val url: String
) {
    val categoryUrl = getImageUrl()
    val categoryName = getCatName()

    private fun getImageUrl(): String =
        "$IMAGE_BASE_URL$CATEGORIES_URL${getCatName()}$IMAGE_EXTENSION"

    private fun getCatName(): String =
        if (name == PEOPLE) {
            CHARACTER
        } else {
            name
        }
}
