package com.example.test.domain.usecases.home

data class ListGetParams(
    val page: Int,
    val search: String? = null
)
