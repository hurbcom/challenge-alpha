package com.example.test.utils

import com.example.core.base.presentation.CategoryType

object ImageHelper {
    private const val IMAGE_BASE_PEOPLE = "https://starwars-visualguide.com/assets/img/characters/"
    private const val IMAGE_BASE_PLANETS = "https://starwars-visualguide.com/assets/img/planets/"
    private const val IMAGE_BASE_STARSHIPS =
        "https://starwars-visualguide.com/assets/img/starships/"

    fun getImage(path: Int, type: CategoryType): String {
        return when (type) {
            CategoryType.PEOPLE -> return getPeopleImage(path)
            CategoryType.PLANETS -> return getPlanetsImage(path)
            else -> getStarshipsImage(path)
        }
    }

    private fun getPeopleImage(path: Int): String = "$IMAGE_BASE_PEOPLE$path.jpg"
    private fun getPlanetsImage(path: Int): String = "$IMAGE_BASE_PLANETS$path.jpg"
    private fun getStarshipsImage(path: Int): String = "$IMAGE_BASE_STARSHIPS$path.jpg"
}