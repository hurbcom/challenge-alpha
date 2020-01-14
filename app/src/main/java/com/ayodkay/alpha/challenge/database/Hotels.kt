package com.ayodkay.alpha.challenge.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ayodkay.alpha.challenge.model.Amenities

@Entity(tableName = "hotel_table")
data class Hotels(@PrimaryKey
                        @ColumnInfo(name = "name") val name: String,
                        @ColumnInfo(name = "price") val price: String,
                        @ColumnInfo(name = "details") val details: String,
                        @ColumnInfo(name = "address") val address: String,
                        @ColumnInfo(name = "images") val images: ArrayList<ArrayList<String>>,
                        @ColumnInfo(name = "amenities") val amenities: ArrayList<ArrayList<Amenities>>)