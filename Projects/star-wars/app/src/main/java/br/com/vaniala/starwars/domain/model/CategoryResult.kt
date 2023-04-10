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
    @field:Json(name = "") val films: String,
    @field:Json(name = "") val people: String,
    @field:Json(name = "") val planets: String,
    @field:Json(name = "") val species: String,
    @field:Json(name = "") val starships: String,
    @field:Json(name = "") val vehicles: String,
)
