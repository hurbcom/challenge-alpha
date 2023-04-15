package br.com.hurbandroidchallenge.domain.model

data class PagedList<T>(
    val next: String?,
    val previous: String?,
    val results: List<T>
)