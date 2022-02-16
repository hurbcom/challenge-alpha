package com.isranascimento.hotels.repository

import com.isranascimento.database.IInsertHotelOnDatabase
import com.isranascimento.databasedtos.hotels.HotelDatabaseEntity
import com.isranascimento.coremodels.hotel.Hotel
import com.isranascimento.hotels.TimerHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HotelsDetailRepository(
    private val dao: IInsertHotelOnDatabase
) : IHotelsDetailRepository {
    override suspend fun insertIntoLastViewed(hotel: Hotel) = withContext(Dispatchers.IO) {
        dao.insertHotel(
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
            insertedTime = TimerHelper.getNow(),
            starCount = this.starCount,
            description = this.description,
            url = this.url
        )
    }
}