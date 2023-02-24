package com.example.test.data.datasources.network

import com.example.test.data.datasources.network.models.PersonResponse
import com.example.test.data.datasources.network.models.PlanetResponse
import com.example.test.data.datasources.network.models.StarshipResponse

class StarWarsMockApi : StarWarsApi {
    override suspend fun getPeople(
        page: Int,
        search: String
    ): StarWarsBaseResponse<PersonResponse> {
        return StarWarsBaseResponse(
            count = 10,
            next = "http://imageurl.com",
            previous = "http://imageurl.com",
            results = listOf(
                PersonResponse(
                    birthYear = "1994",
                    created = "2023",
                    edited = "2023",
                    eyeColor = "black",
                    films = listOf(),
                    gender = "male",
                    hairColor = "brown",
                    height = "177",
                    homeWorld = "http://imageurl.com",
                    mass = "90",
                    name = "Eduardo",
                    skinColor = "white",
                    species = listOf(),
                    starships = listOf(),
                    url = "https://swapi.dev/api/people/1",
                    vehicles = listOf()
                )
            )
        )
    }

    override suspend fun getPlanets(
        page: Int,
        search: String
    ): StarWarsBaseResponse<PlanetResponse> {
        return StarWarsBaseResponse(
            count = 10,
            next = "http://imageurl.com",
            previous = "http://imageurl.com",
            results = listOf(
                PlanetResponse(
                    climate = "Tropical",
                    created = "2022",
                    diameter = "20000",
                    edited = "2022",
                    films = listOf(),
                    gravity = "10",
                    name = "Terra",
                    orbitalPeriod = "365",
                    population = "8000000000",
                    residents = listOf(),
                    rotationPeriod = "24",
                    surfaceWater = "water",
                    terrain = "montanhoso",
                    url = "https://swapi.dev/api/planets/2"
                )
            )
        )
    }

    override suspend fun getStarships(
        page: Int,
        search: String
    ): StarWarsBaseResponse<StarshipResponse> {
        return StarWarsBaseResponse(
            count = 10,
            next = "http://imageurl.com",
            previous = "http://imageurl.com",
            results = listOf(
                StarshipResponse(
                    cargoCapacity = "5",
                    consumables = "30",
                    costInCredits = "30000",
                    created = "2022",
                    crew = "",
                    edited = "2022",
                    films = listOf(),
                    hyperdriveRating = "500",
                    length = "5",
                    mGLT = "",
                    manufacturer = "Volkswagen",
                    maxAtmospheringSpeed = "220",
                    model = "1.6 tsi",
                    name = "Fusca",
                    passengers = "5",
                    pilots = listOf(),
                    starshipClass = "Hatch",
                    url = "https://swapi.dev/api/starships/5"
                )
            )
        )
    }
}