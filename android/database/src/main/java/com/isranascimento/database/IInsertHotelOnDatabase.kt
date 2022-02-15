package com.isranascimento.database

import com.isranascimento.databasedtos.hotels.HotelDatabaseEntity

interface IInsertHotelOnDatabase {
    suspend fun insertHotel(
        hotel: HotelDatabaseEntity,
        amenities: List<String>,
        gallery: List<String>
    )
}