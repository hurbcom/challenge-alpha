package br.com.gstoduto.starwars.model

import androidx.room.ColumnInfo

data class Specie(
    @ColumnInfo(name = "id") var id: Long  = 0L,
    val name: String,
    val classification: String,
    val designation: String,
    @ColumnInfo(name = "average_height") var averageHeight: String?  = null,
    @ColumnInfo(name = "skin_colors") var skinColors: String?  = null,
    @ColumnInfo(name = "hair_colors") var hairColors: String?  = null,
    @ColumnInfo(name = "eye_colors") var eyeColors: String?  = null,
    @ColumnInfo(name = "average_lifespan") var averageLifespan: String?  = null,
    val language: String,
    val url: String,
    val inMyList: Boolean = false
)