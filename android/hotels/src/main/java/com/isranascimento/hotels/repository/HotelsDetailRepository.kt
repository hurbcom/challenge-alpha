package com.isranascimento.hotels.repository

import com.isranascimento.database.HotelsDAO
import com.isranascimento.databasedtos.hotels.HotelDatabaseEntity
import com.isranascimento.hotels.TimerHelper
import com.isranascimento.hotels.models.Hotel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HotelsDetailRepository(
    private val dao: HotelsDAO
) : IHotelsDetailRepository {
    override suspend fun insertIntoLastViewed(hotel: Hotel) = withContext(Dispatchers.IO) {
        dao.insertHotelWithAmenities(
            hotel.asDatabaseModel(),
            hotel.amenities,
            hotel.gallery
        )
    }

    private fun Hotel.asDatabaseModel(): HotelDatabaseEntity {
        return HotelDatabaseEntity(
            id = this.id,
            city = this.address.city,
            state = this.address.state,
            mainImage = this.mainImage,
            name = this.name,
            insertedTime = TimerHelper.getNow()
        )
    }
}