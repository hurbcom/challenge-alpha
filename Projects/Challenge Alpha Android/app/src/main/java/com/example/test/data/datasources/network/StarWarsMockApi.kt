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
                    url = "http://imageurl.com",
                    vehicles = listOf()
                )
            )
        )
    }

    override suspend fun getPlanets(
        page: Int,
        search: String
    ): StarWarsBaseResponse<PlanetResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getStarships(
        page: Int,
        search: String
    ): StarWarsBaseResponse<StarshipResponse> {
        TODO("Not yet implemented")
    }

}