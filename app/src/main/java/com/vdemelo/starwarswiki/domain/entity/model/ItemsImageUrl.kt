package com.vdemelo.starwarswiki.domain.entity.model

enum class ItemsImageUrl(
    val baseUrl: String,
    val extension: String
) {
    SPECIES(
        baseUrl = "https://starwars-visualguide.com/assets/img/species/",
        extension = ".jpg"
    ),
    PLANET(
        baseUrl = "https://starwars-visualguide.com/assets/img/planets/",
        extension = ".jpg"
    )
}

fun ItemsImageUrl.getImageUrl(itemNumber: String): String {
    return this.baseUrl + itemNumber + this.extension
}
