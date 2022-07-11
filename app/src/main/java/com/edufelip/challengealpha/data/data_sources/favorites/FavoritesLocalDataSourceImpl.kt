package com.edufelip.challengealpha.data.data_sources.favorites

import com.edufelip.challengealpha.data.local.room.FavoriteDao
import com.edufelip.challengealpha.data.local.room.models.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesLocalDataSourceImpl @Inject constructor(
    private val favoritesDao: FavoriteDao
) : FavoritesLocalDataSource {

    override fun getFavoritesList(): List<Favorite> {
        return favoritesDao.getAllFavorites()
    }

    override suspend fun insertFavorite(favorite: Favorite) {
        favoritesDao.insertFavorite(favorite)
    }

    override suspend fun deleteFavorite(favorite: Favorite) {
        favoritesDao.deleteFavorite(favorite)
    }
}