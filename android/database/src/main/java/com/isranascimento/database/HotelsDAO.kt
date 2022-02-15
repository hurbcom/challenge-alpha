package com.isranascimento.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.isranascimento.databasedtos.hotels.HotelDatabaseEntity
import com.isranascimento.databasedtos.hotels.HotelsAmenityDatabaseEntity
import com.isranascimento.databasedtos.hotels.HotelsGalleryItemDatabaseEntity
import com.isranascimento.databasedtos.hotels.HotelsWithAmenitiesEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class HotelsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertHotel(createHotelEntity: HotelDatabaseEntity): Long

    @Insert
    abstract suspend fun insertAmenities(list: List<HotelsAmenityDatabaseEntity>)

    @Insert
    abstract suspend fun insertGalleryItems(list: List<HotelsGalleryItemDatabaseEntity>)

    @Transaction
    open suspend fun insertHotelWithAmenities(
        hotel: HotelDatabaseEntity,
        amenities: List<String>,
        gallery: List<String>
    ) {
        insertHotel(hotel)
        val amenitiesToInsert: List<HotelsAmenityDatabaseEntity> = amenities.map {
            HotelsAmenityDatabaseEntity(
                value = it,
                hotelId = hotel.id
            )
        }
        val galleryToInsert = gallery.map {
            HotelsGalleryItemDatabaseEntity(
                value = it,
                hotelId = hotel.id
            )
        }
        insertAmenities(amenitiesToInsert)
        insertGalleryItems(galleryToInsert)
    }

    @Query("SELECT * from hotels ORDER BY insertedTime DESC")
    abstract fun getHotelsWithAmenities(): Flow<List<HotelsWithAmenitiesEntity>>
}