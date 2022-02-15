package com.isranascimento.databasedtos.hotels

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "hotels_amenity", foreignKeys = [
    ForeignKey(
        entity = HotelsAmenityDatabaseEntity::class,
        parentColumns = ["id"],
        childColumns = ["hotelId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )
])
data class HotelsAmenityDatabaseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    val id: Long = 0,

    @ColumnInfo(name = "value")
    @NonNull 
    val value: String,
    
    @ColumnInfo(name = "hotelId")
    @NonNull 
    val hotelId: String,

)