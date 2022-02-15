package com.isranascimento.databasedtos.hotels

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hotels_amenity")
data class HotelsAmenityDatabaseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    val id: Long,

    @ColumnInfo(name = "value")
    @NonNull 
    val value: String,
    
    @ColumnInfo(name = "hotelId")
    @NonNull 
    val hotelId: String,

)