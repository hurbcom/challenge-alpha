package com.edufelip.challengealpha.data.repositories

import android.content.Context
import com.edufelip.challengealpha.data.local.room.models.Favorite
import com.edufelip.challengealpha.domain.models.GeneralListMenuItem
import com.edufelip.challengealpha.domain.models.GeneralListMenuItemTypeEnum
import com.edufelip.challengealpha.domain.repositories.FavoritesRepository
import com.edufelip.challengealpha.domain.repositories.GeneralListMenuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavoritesFakeRepository : FavoritesRepository{
    override fun getFavoritesList(): Flow<List<Favorite>> {
        return flow {
            emit(mutableListOf(Favorite(
                name = "name",
                imageUrl = "imageUrl",
                url = "url"
            )))
        }
    }

    override fun insertFavorite(favorite: Favorite): Flow<Boolean> {
        return flow {
            emit(true)
        }
    }

    override fun deleteFavorite(favorite: Favorite): Flow<Boolean> {
        return flow {
            emit(true)
        }
    }
}