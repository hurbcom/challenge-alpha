package com.isranascimento.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.isranascimento.databasedtos.hotels.HotelDatabaseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HotelsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotel(createHotelEntity: HotelDatabaseEntity)

    @Query("SELECT * from hotels ORDER BY insertedTime DESC")
    fun getHotelList(): Flow<List<HotelDatabaseEntity>>
}