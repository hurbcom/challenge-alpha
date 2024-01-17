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

    private fun getImageUrl(): String {
        val type = if (name == PEOPLE) {
            CHARACTER
        } else name

        return "$IMAGE_BASE_URL$CATEGORIES_URL$type$IMAGE_EXTENSION"
    }
}
