package br.com.vaniala.starwars.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
@JsonClass(generateAdapter = true)
data class CategoryResult(
    @field:Json(name = "films") val films: String,
    @field:Json(name = "people") val people: String,
    @field:Json(name = "planets") val planets: String,
    @field:Json(name = "species") val species: String,
    @field:Json(name = "starships") val starships: String,
    @field:Json(name = "vehicles") val vehicles: String,
)
