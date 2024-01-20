package br.com.mdr.starwars.domain.model

sealed class PageState<out T : Any> {
    data class Success<T : Any>(val result: T) : PageState<T>()
    data class Error(val error: Throwable) : PageState<Nothing>()
    data object Loading : PageState<Nothing>()
    data object Empty : PageState<Nothing>()
}
