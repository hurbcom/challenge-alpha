package br.com.hurbandroidchallenge.data.mapper.categories

import br.com.hurbandroidchallenge.data.local.model.HomeCategoriesEntity
import br.com.hurbandroidchallenge.data.remote.model.HomeCategoriesDto
import br.com.hurbandroidchallenge.domain.model.Categories

fun HomeCategoriesEntity.toCategories() = this.run {
    Categories.values().first { url.contains(it.url) }
}

fun HomeCategoriesDto.toEntity() = this.run {
    val urls = mutableListOf<String?>()
    urls.add(people)
    urls.add(films)
//        urls.add(species)
//        urls.add(starships)
//        urls.add(vehicles)
//        urls.add(planets)
    val enabledCategories = Categories.values().filter { urls.contains(it.url) }
    enabledCategories.map { category ->
        HomeCategoriesEntity(url = category.url)
    }
}