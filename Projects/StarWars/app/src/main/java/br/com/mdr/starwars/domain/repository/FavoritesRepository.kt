package br.com.mdr.starwars.domain.repository

import br.com.mdr.starwars.domain.model.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    suspend fun getFavorites(): Flow<Favorite>
}