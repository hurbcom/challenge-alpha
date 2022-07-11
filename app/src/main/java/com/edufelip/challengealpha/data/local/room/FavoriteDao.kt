package com.edufelip.challengealpha.data.local.room

import androidx.room.*
import com.edufelip.challengealpha.data.local.room.models.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite_table")
    fun getAllFavorites(): List<Favorite>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)
}