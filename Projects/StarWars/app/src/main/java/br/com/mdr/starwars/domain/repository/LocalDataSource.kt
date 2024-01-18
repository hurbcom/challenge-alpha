package br.com.mdr.starwars.domain.repository

import br.com.mdr.starwars.domain.model.Film

interface LocalDataSource {
    suspend fun getSelectedFilm(filmId: Int): Film
    suspend fun setFavorite(isFavorite: Boolean, filmId: Int)
}