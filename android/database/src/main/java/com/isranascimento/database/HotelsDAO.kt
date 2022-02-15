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
        insertAmenities(getAmenitiesToInsert(amenities, hotel))
        insertGalleryItems(getGalleryToInsert(gallery, hotel))
    }

    private fun getAmenitiesToInsert(
        amenities: List<String>,
        hotel: HotelDatabaseEntity
    ): List<HotelsAmenityDatabaseEntity> {
        return amenities.map {
            HotelsAmenityDatabaseEntity(
                value = it,
                hotelId = hotel.id
            )
        }
    }

    private fun getGalleryToInsert(
        gallery: List<String>,
        hotel: HotelDatabaseEntity
    ): List<HotelsGalleryItemDatabaseEntity> {
        return gallery.map {
            HotelsGalleryItemDatabaseEntity(
                value = it,
                hotelId = hotel.id
            )
        }
    }

    @Query("SELECT * from hotels ORDER BY insertedTime DESC")
    abstract fun getHotelsWithAmenities(): Flow<List<HotelsWithAmenitiesEntity>>
}