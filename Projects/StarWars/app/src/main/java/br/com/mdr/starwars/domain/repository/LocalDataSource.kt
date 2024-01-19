package br.com.mdr.starwars.domain.repository

import br.com.mdr.starwars.domain.model.Character
import br.com.mdr.starwars.domain.model.Film

interface LocalDataSource {
    suspend fun getSelectedFilm(filmId: Int): Film
    suspend fun setFavoriteMovie(isFavorite: Boolean, filmId: Int)

    suspend fun getSelectedCharacter(name: String): Character
    suspend fun setFavoriteCharacter(isFavorite: Boolean, name: String)
}