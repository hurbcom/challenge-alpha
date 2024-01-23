package com.br.myapplication.data.mapper

import com.br.myapplication.data.model.Film
import com.br.myapplication.data.model.Specie
import com.br.myapplication.extensions.categoryImageUrl

fun List<Specie>.mapListWithImage(): List<Specie> {
    for (item in this) {
        item.image = item.url.categoryImageUrl("species")
    }
    return this;
}