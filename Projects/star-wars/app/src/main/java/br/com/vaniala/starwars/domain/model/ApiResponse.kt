package br.com.vaniala.starwars.domain.model

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 11/04/23.
 *
 */
data class ApiResponse<out T : Any>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<T>,
)
