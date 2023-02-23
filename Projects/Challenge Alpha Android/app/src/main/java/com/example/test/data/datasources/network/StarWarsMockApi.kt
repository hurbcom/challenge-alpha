package com.example.test.data.datasources.network

import com.example.test.data.datasources.network.models.PersonResponse
import com.example.test.data.datasources.network.models.PlanetResponse
import com.example.test.data.datasources.network.models.StarshipResponse

class StarWarsMockApi : StarWarsApi {
    override suspend fun getPeople(
        page: Int,
        search: String
    ): StarWarsBaseResponse<PersonResponse> {
        TODO("Not yet implemented")
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