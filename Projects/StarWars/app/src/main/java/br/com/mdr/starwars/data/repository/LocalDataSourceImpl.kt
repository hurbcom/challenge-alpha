package br.com.mdr.starwars.data.repository

import br.com.mdr.starwars.data.local.AppDatabase
import br.com.mdr.starwars.domain.model.Character
import br.com.mdr.starwars.domain.model.Film
import br.com.mdr.starwars.domain.repository.LocalDataSource

class LocalDataSourceImpl(database: AppDatabase): LocalDataSource {

    private val filmDao = database.getFilmDao()
    private val characterDao = database.getCharactersDao()

    override suspend fun getSelectedFilm(filmId: Int): Film {
        return filmDao.getSelectedFilm(filmId)
    }
    override suspend fun setFavoriteMovie(isFavorite: Boolean, filmId: Int) {
        filmDao.updateIsFavorite(isFavorite, filmId)
    }
    override suspend fun getSelectedCharacter(name: String): Character {
        return characterDao.getSelectedCharacter(name)
    }
    override suspend fun setFavoriteCharacter(isFavorite: Boolean, name: String) {
        characterDao.updateIsFavorite(isFavorite, name)
    }
}