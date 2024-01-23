package com.br.myapplication.data.repository.film

import androidx.paging.PagingData
import com.br.myapplication.data.model.Film
import kotlinx.coroutines.flow.Flow

interface IFilmRepository {
    suspend fun fetchFilmList(page: String): List<Film>
    suspend fun saveFilm(film: Film)
}