package com.br.myapplication.data.mapper

import com.br.myapplication.data.model.Film
import com.br.myapplication.extensions.categoryImageUrl

fun List<Film>.mapListWithImage(): List<Film> {
    for (item in this) {
        item.image = item.url.categoryImageUrl("films")
    }
    return this;
}