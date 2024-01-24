package com.br.myapplication.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "planets_table")
data class Planet(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var image: String = "",
    val climate: String,
    val created: String,
    val diameter: String,
    val edited: String,
    val films: List<String>,
    val gravity: String,
    val name: String,

    @SerializedName("orbital_period")
    val orbitalPeriod: String,
    val population: String,
    val residents: List<String>,

    @SerializedName("rotation_period")
    val rotationPeriod: String,

    @SerializedName("surface_water")
    val surfaceWater: String,
    val terrain: String,
    val url: String,
    var isFavorite: Boolean = false
)