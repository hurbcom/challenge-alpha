package com.br.myapplication.data.remote

import com.br.myapplication.data.model.Film
import com.br.myapplication.data.model.FilmsPage
import com.br.myapplication.data.model.Planet
import com.br.myapplication.data.model.Specie


class MockApi : ApiServices {
    override suspend fun getFilms(page: String): FilmsPage {
        return FilmsPage(
            nextPage = null,
            previousPage = null,
            movieList = emptyList()
        )
    }

    override suspend fun getPlanets(page: String): List<Planet> {
        return listOf()
    }

    override suspend fun getSpecies(page: String): List<Specie> {
        return listOf()
    }

}