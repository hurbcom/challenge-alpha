package com.edufelip.challengealpha.data.repositories

import com.edufelip.challengealpha.data.data_sources.favorites.FavoritesLocalDataSource
import com.edufelip.challengealpha.data.local.room.models.Favorite
import com.edufelip.challengealpha.domain.repositories.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val localDataSource: FavoritesLocalDataSource
) : FavoritesRepository {
    override fun getFavoritesList(): Flow<List<Favorite>> {
        return flow {
            emit(localDataSource.getFavoritesList())
        }.flowOn(Dispatchers.IO)
    }

    override fun insertFavorite(favorite: Favorite): Flow<Boolean> {
        return flow {
            try {
                localDataSource.insertFavorite(favorite)
            } catch (throwable: Throwable) {
                throw Throwable(throwable.message)
            }
            emit(true)
        }.flowOn(Dispatchers.IO)
    }

    override fun deleteFavorite(favorite: Favorite): Flow<Boolean> {
        return flow {
            try {
                localDataSource.deleteFavorite(favorite)
            } catch (throwable: Throwable) {
                throw Throwable(throwable.message)
            }
            emit(true)
        }.flowOn(Dispatchers.IO)
    }
}