package br.com.vaniala.starwars.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 11/04/23.
 *
 */

@JsonClass(generateAdapter = true)
data class People(
    @field:Json(name = "birth_year") val birthYear: String,
    @field:Json(name = "created") val created: String,
    @field:Json(name = "edited") val edited: String,
    @field:Json(name = "eye_color") val eyeColor: String,
    @field:Json(name = "gender") val gender: String,
    @field:Json(name = "hair_color") val hairColor: String,
    @field:Json(name = "height") val height: String,
    @field:Json(name = "homeworld") val homeWorld: String,
    @field:Json(name = "mass") val mass: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "skin_color") val skinColor: String,
    @field:Json(name = "url") val url: String,
)
