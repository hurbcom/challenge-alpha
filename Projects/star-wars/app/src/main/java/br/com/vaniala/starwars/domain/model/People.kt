package br.com.vaniala.starwars.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 11/04/23.
 *
 */

@Entity(
    tableName = "character",
)
@Parcelize
@JsonClass(generateAdapter = true)
data class People(
    @PrimaryKey
    val name: String,
    @field:Json(name = "birth_year") val birth_year: String? = null,
    @field:Json(name = "created") val created: String? = null,
    @field:Json(name = "edited") val edited: String? = null,
    @field:Json(name = "eye_color") val eye_color: String? = null,
    @field:Json(name = "gender") val gender: String? = null,
    @field:Json(name = "hair_color") val hair_color: String? = null,
    @field:Json(name = "height") val height: String? = null,
    @field:Json(name = "homeworld") var homeworld: String? = null,
    @field:Json(name = "mass") val mass: String? = null,
    @field:Json(name = "skin_color") val skin_color: String? = null,
    @field:Json(name = "url")
    @ColumnInfo(name = "url_character")
    val url: String? = null,
    @field:Json(ignore = true) var imagePeople: String? = null,
    @field:Json(ignore = true) var imageHomeworld: String? = null,
    @field:Json(ignore = true)
    @ColumnInfo(name = "isFavorite_character")
    var isFavorite: Boolean = false,
    @field:Json(ignore = true)
    @ColumnInfo(name = "timestamp_character")
    var timestamp: Long = 0L,
) : Parcelable
