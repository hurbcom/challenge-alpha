package com.br.myapplication.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "species_table")
data class Specie(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var image: String = "",
    @SerializedName("average_height")
    val averageHeight: String,

    @SerializedName("average_lifespan")
    val averageLifespan: String,
    val classification: String,
    val created: String,
    val designation: String,
    val edited: String,

    @SerializedName("eye_colors")
    val eyeColors: String,
    val films: List<String>,

    @SerializedName("hair_colors")
    val hairColors: String,

    @SerializedName("homeworld")
    val homeWorld: String?,
    val language: String,
    val name: String,

    val people: List<String>,

    @SerializedName("skin_colors")
    val skinColors: String,
    val url: String
)