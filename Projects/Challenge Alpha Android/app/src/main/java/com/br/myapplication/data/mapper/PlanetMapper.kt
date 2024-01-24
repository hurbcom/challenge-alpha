package com.br.myapplication.data.mapper

import com.br.myapplication.data.model.Favorite

import com.br.myapplication.data.model.Planet
import com.br.myapplication.extensions.categoryImageUrl

fun List<Planet>.mapListWithImage(): List<Planet> {

    for (item in this) {
        item.image = item.url.categoryImageUrl("planets")
    }
    return this;
}
fun List<Planet>.mapToFavoriteList(): List<Favorite> = this.map {
    Favorite(
        firstProperty = it.name ,
        secondProperty = it.image,
        thirdProperty = it.climate,
        fourthProperty = it.population,
        fifthProperty = it.terrain
    )
}