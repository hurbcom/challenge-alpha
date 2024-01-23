package com.br.myapplication.data.repository.film

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.br.myapplication.data.dao.FilmsDao
import com.br.myapplication.data.mapper.mapListWithImage
import com.br.myapplication.data.model.Film
import com.br.myapplication.data.remote.ApiServices
import com.br.myapplication.data.remote.FilmsPagingSource
import kotlinx.coroutines.flow.Flow

class FilmRepository(
    private val apiService: ApiServices,
    private val dao: FilmsDao
): IFilmRepository {

    override suspend fun fetchFilmList(page: String): List<Film> {
        return apiService.getFilms(page).movieList.mapListWithImage()
    }

    override suspend fun saveFilm(film: Film) {
        dao.insertFilm(film)
    }
}