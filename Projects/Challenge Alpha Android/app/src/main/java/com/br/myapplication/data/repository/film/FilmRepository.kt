package com.br.myapplication.data.repository.film

import com.br.myapplication.data.mapper.mapListWithImage
import com.br.myapplication.data.model.Film
import com.br.myapplication.data.remote.ApiServices

class FilmRepository(
    private val apiService: ApiServices
): IFilmRepository {

    override suspend fun getFilmList(page: String): List<Film> {
        return apiService.getFilms(page).movieList.mapListWithImage()
    }
}