package br.com.mdr.starwars.domain.repository

import br.com.mdr.starwars.domain.model.Character
import br.com.mdr.starwars.domain.model.Favorite
import br.com.mdr.starwars.domain.model.Film
import br.com.mdr.starwars.domain.model.LastSeen
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun getSelectedFilm(filmId: Int): Film
    suspend fun setFavoriteMovie(isFavorite: Boolean, filmId: Int)

    suspend fun getSelectedCharacter(name: String): Character
    suspend fun setFavoriteCharacter(isFavorite: Boolean, name: String)
    suspend fun getFavorites(): Flow<Favorite>
    suspend fun getLastSeen(): Flow<List<LastSeen>>
    suspend fun saveLastSeen(lastSeen: LastSeen)
}
