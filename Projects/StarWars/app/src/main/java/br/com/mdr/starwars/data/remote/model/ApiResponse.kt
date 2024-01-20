package br.com.mdr.starwars.data.remote.model

data class ApiResponse<T>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<T>
)
