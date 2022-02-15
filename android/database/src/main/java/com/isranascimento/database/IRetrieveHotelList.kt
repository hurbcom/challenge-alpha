package com.isranascimento.database

import com.isranascimento.databasedtos.hotels.HotelDatabaseEntity
import com.isranascimento.databasedtos.hotels.HotelsWithAmenitiesEntity
import kotlinx.coroutines.flow.Flow

interface IRetrieveHotelList {
    fun getHotelsWithAmenities(): Flow<List<HotelsWithAmenitiesEntity>>
}