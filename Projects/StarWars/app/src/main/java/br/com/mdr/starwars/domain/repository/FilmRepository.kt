package br.com.mdr.starwars.domain.repository

import androidx.paging.PagingData
import br.com.mdr.starwars.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface FilmRepository {
    fun getFilms(): Flow<PagingData<Film>>
    fun searchFilms(query: String): Flow<PagingData<Film>>
    suspend fun getFilm(id: Int): Film
    suspend fun setFavorite(isFavorite: Boolean, filmId: Int)
}