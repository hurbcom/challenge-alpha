package br.com.mdr.starwars.data.repository

import br.com.mdr.starwars.data.local.AppDatabase
import br.com.mdr.starwars.domain.model.Character
import br.com.mdr.starwars.domain.model.Favorite
import br.com.mdr.starwars.domain.model.Film
import br.com.mdr.starwars.domain.repository.LocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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
    override suspend fun getFavorites(): Flow<Favorite> = flow {
        val films = filmDao.getFavoriteFilms()
        val characters = characterDao.getFavoriteCharacters()
        emit(Favorite(films, characters))
    }
}