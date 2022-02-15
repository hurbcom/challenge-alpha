package com.isranascimento.lastviewed.repository

import com.isranascimento.database.IRetrieveHotelList
import com.isranascimento.databasedtos.hotels.HotelsWithAmenitiesEntity
import kotlinx.coroutines.flow.Flow

class LastViewedRepository(
    private val retrieveHotelList: IRetrieveHotelList
) {
    fun getLastViewed(): Flow<List<HotelsWithAmenitiesEntity>> {
        return retrieveHotelList.getHotelsWithAmenities()
    }
}