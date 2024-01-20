package br.com.mdr.starwars.data.remote.model

data class BaseApiResponse<out T : Any>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<T>
)
