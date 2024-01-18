package br.com.mdr.starwars.data.repository

import br.com.mdr.starwars.data.local.AppDatabase
import br.com.mdr.starwars.domain.model.Film
import br.com.mdr.starwars.domain.repository.LocalDataSource

class LocalDataSourceImpl(database: AppDatabase): LocalDataSource {

    private val filmDao = database.getFilmDao()

    override suspend fun getSelectedFilm(filmId: Int): Film {
        return filmDao.getSelectedFilm(filmId)
    }

    override suspend fun setFavorite(isFavorite: Boolean, filmId: Int) {
        filmDao.updateIsFavorite(isFavorite, filmId)
    }
}