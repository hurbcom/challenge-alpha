package br.com.vaniala.starwars.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 11/04/23.
 *
 */

@Parcelize
@JsonClass(generateAdapter = true)
data class People(
    @field:Json(name = "birth_year") val birth_year: String?,
    @field:Json(name = "created") val created: String?,
    @field:Json(name = "edited") val edited: String?,
    @field:Json(name = "eye_color") val eye_color: String?,
    @field:Json(name = "gender") val gender: String?,
    @field:Json(name = "hair_color") val hair_color: String?,
    @field:Json(name = "height") val height: String?,
    @field:Json(name = "homeworld") var homeworld: String?,
    @field:Json(name = "mass") val mass: String?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "skin_color") val skin_color: String?,
    @field:Json(name = "url") val url: String?,
    @field:Json(ignore = true) var imagePeople: String?,
    @field:Json(ignore = true) var imageHomeworld: String?,
) : Parcelable
