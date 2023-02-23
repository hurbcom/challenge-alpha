package com.example.test.utils

object ImageHelper {
    private const val PEOPLE_BASE_URL = "https://swapi.dev/api/people/"
    private const val PLANETS_BASE_URL = "https://swapi.dev/api/planets/"
    private const val STARSHIPS_BASE_URL = "https://swapi.dev/api/starships/"

    private const val IMAGE_BASE_PEOPLE = "https://starwars-visualguide.com/assets/img/characters/"
    private const val IMAGE_BASE_PLANETS = "https://starwars-visualguide.com/assets/img/planets/"
    private const val IMAGE_BASE_STARSHIPS = "https://starwars-visualguide.com/assets/img/starships/"

    fun getPeopleImage(url: String): String =
        "${url.replace(PEOPLE_BASE_URL, IMAGE_BASE_PEOPLE).removeSuffix("/")}.jpg"

    fun getPlanetsImage(url: String): String =
        "${url.replace(PLANETS_BASE_URL, IMAGE_BASE_PLANETS).removeSuffix("/")}.jpg"

    fun getStarshipsImage(url: String): String =
        "${url.replace(STARSHIPS_BASE_URL, IMAGE_BASE_STARSHIPS).removeSuffix("/")}.jpg"
}