package br.com.mdr.starwars.data.remote.model

import br.com.mdr.starwars.domain.model.Film

data class BaseApiResponse<out T>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<T>
)

interface IBaseApiResponse {
    val count: Int
    val next: String?
    val previous: String?
}
