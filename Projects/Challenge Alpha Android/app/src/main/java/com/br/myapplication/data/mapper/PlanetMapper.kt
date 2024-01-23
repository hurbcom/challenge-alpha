package com.br.myapplication.data.mapper

import com.br.myapplication.data.model.Film
import com.br.myapplication.data.model.Planet
import com.br.myapplication.extensions.categoryImageUrl

fun List<Planet>.mapListWithImage(): List<Planet> {

    for (item in this) {
        item.image = item.url.categoryImageUrl("planets")
    }
    return this;
}