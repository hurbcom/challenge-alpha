package com.edufelip.challengealpha.domain.repositories

import com.edufelip.challengealpha.data.local.room.models.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getFavoritesList(): Flow<List<Favorite>>
    fun insertFavorite(favorite: Favorite): Flow<Boolean>
    fun deleteFavorite(favorite: Favorite): Flow<Boolean>
}