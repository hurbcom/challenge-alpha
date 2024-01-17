package br.com.mdr.starwars.domain.repository

import androidx.paging.PagingData
import br.com.mdr.starwars.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllFilms(): Flow<PagingData<Film>>
    fun searchFilms(query: String): Flow<PagingData<Film>>
}