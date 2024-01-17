package br.com.mdr.starwars.data.repository

import androidx.paging.PagingData
import br.com.mdr.starwars.domain.model.Film
import br.com.mdr.starwars.domain.repository.FilmRepository
import br.com.mdr.starwars.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class FilmRepositoryImpl(
    private val dataSource: RemoteDataSource,
): FilmRepository {

    override fun getFilms(): Flow<PagingData<Film>> =
        dataSource.getAllFilms()

    override fun searchFilms(query: String): Flow<PagingData<Film>> =
        dataSource.searchFilms(query)
}
