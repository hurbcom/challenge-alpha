package br.com.hurbandroidchallenge.domain.model.base

data class PagedList<T>(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<T>
)