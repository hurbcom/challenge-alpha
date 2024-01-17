package br.com.mdr.starwars.domain.repository

import androidx.paging.PagingData
import br.com.mdr.starwars.data.remote.model.BaseApiResponse
import br.com.mdr.starwars.data.remote.model.FilmResponse
import br.com.mdr.starwars.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface FilmRepository {
    fun getFilms(): Flow<PagingData<Film>>

    fun searchFilms(query: String): Flow<PagingData<Film>>
}