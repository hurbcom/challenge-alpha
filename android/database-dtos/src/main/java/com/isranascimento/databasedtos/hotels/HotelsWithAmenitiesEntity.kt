package com.isranascimento.databasedtos.hotels

import androidx.room.Embedded
import androidx.room.Relation

data class HotelsWithAmenitiesEntity(
    @Embedded
    val hotel: HotelDatabaseEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "hotelId"
    )
    val amenities: List<HotelsAmenityDatabaseEntity>
)