package com.ayodkay.alpha.challenge.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.json.JSONObject

@Entity(tableName = "hotel_table")
data class Hotels(@PrimaryKey
                        @ColumnInfo(name = "json") val hotelsJson: JSONObject
)