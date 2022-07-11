package com.edufelip.challengealpha.data.data_sources.favorites

import com.edufelip.challengealpha.data.local.room.models.Favorite

interface FavoritesLocalDataSource {
    fun getFavoritesList(): List<Favorite>
    suspend fun insertFavorite(favorite: Favorite)
    suspend fun deleteFavorite(favorite: Favorite)
}