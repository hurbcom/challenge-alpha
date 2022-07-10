package com.edufelip.challengealpha.domain.models.base

data class PagedList<T>(
    val meta: Meta,
    val items: List<T>
)