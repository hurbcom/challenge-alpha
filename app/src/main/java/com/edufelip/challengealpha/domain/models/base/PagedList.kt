package com.edufelip.challengealpha.domain.models.base

data class PagedList<T>(
    val next: String?,
    val results: List<T>
)