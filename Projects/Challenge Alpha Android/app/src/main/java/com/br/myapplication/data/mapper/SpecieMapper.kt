package com.br.myapplication.data.mapper

import com.br.myapplication.data.model.Favorite
import com.br.myapplication.data.model.Specie
import com.br.myapplication.extensions.categoryImageUrl

fun List<Specie>.mapListWithImage(): List<Specie> {
    for (item in this) {
        item.image = item.url.categoryImageUrl("species")
    }
    return this;
}

fun List<Specie>.mapToFavoriteList(): List<Favorite> = this.map {
    Favorite(
        firstProperty = it.name ,
        secondProperty = it.image,
        thirdProperty = it.language,
        fourthProperty = it.classification,
        fifthProperty = it.designation
    )
}