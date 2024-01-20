package br.com.mdr.starwars.data.repository

import androidx.paging.PagingData
import br.com.mdr.starwars.domain.model.Film
import br.com.mdr.starwars.domain.model.LastSeen
import br.com.mdr.starwars.domain.repository.FilmRepository
import br.com.mdr.starwars.domain.repository.LocalDataSource
import br.com.mdr.starwars.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class FilmRepositoryImpl(
    private val dataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): FilmRepository {

    override fun getFilms(): Flow<PagingData<Film>> =
        dataSource.getAllFilms()

    override fun searchFilms(query: String): Flow<PagingData<Film>> =
        dataSource.searchFilms(query)

    override suspend fun getFilm(id: Int): Film =
        localDataSource.getSelectedFilm(id)

    override suspend fun setFavorite(isFavorite: Boolean, filmId: Int) {
        localDataSource.setFavoriteMovie(isFavorite, filmId)
    }

    override suspend fun saveLastSeen(film: Film) {
        val lastSeen = LastSeen(
            lastSeenId = film.id.toString(),
            film = film
        )
        localDataSource.saveLastSeen(lastSeen)
    }
}
