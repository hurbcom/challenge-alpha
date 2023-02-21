package com.example.test.utils

object ImageHelper {
    private const val IMAGE_BASE_PEOPLE = "https://starwars-visualguide.com/assets/img/characters/"
    private const val IMAGE_BASE_PLANETS = "https://starwars-visualguide.com/assets/img/planets/"
    private const val IMAGE_BASE_STARSHIPS = "https://starwars-visualguide.com/assets/img/starships/"

    fun getPeopleImage(path: Int): String = "$IMAGE_BASE_PEOPLE$path.jpg"
    fun getPlanetsImage(path: Int): String = "$IMAGE_BASE_PLANETS$path.jpg"
    fun getStarshipsImage(path: Int): String = "$IMAGE_BASE_STARSHIPS$path.jpg"
}