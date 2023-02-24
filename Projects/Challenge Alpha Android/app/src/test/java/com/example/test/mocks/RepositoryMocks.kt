package com.example.test.mocks

import com.example.test.data.datasources.network.StarWarsBaseResponse
import com.example.test.data.datasources.network.models.PersonResponse

object RepositoryMocks {
    val peopleResponse =
        StarWarsBaseResponse(
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