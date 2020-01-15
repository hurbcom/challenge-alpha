package com.ayodkay.alpha.challenge.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ayodkay.alpha.challenge.model.Address
import com.ayodkay.alpha.challenge.model.Amenities
import com.ayodkay.alpha.challenge.model.Descriptions
import org.json.JSONObject

@Entity(tableName = "hotel_table")
data class Hotels(@PrimaryKey
                        @ColumnInfo(name = "name") val name: String,
                        @ColumnInfo(name = "price") val price: String,
                        @ColumnInfo(name = "huFreeCancellation") val huFreeCancellation: Boolean,
                        @ColumnInfo(name = "details") val details: Descriptions,
                        @ColumnInfo(name = "address") val address: Address,
                        @ColumnInfo(name = "images") val images: ArrayList<ArrayList<String>>,
                        @ColumnInfo(name = "amenities") val amenities: ArrayList<ArrayList<Amenities>>)