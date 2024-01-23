package com.br.myapplication.data.remote



import com.br.myapplication.data.model.ApiResponse
import com.br.myapplication.data.model.Film
import com.br.myapplication.data.model.Planet
import com.br.myapplication.data.model.Specie


class MockApi : ApiServices {
    override suspend fun getFilms(page: String): ApiResponse<Film> {
        return ApiResponse(
            nextPage = null,
            previousPage = null,
            movieList = emptyList()
        )
    }


    override suspend fun getPlanets(page: String): ApiResponse<Planet> {
        return ApiResponse(
            nextPage = null,
            previousPage = null,
            movieList = emptyList()
        )
    }

    override suspend fun getSpecies(page: String): ApiResponse<Specie> {
        return ApiResponse(
            nextPage = null,
            previousPage = null,
            movieList = emptyList()
        )
    }

}