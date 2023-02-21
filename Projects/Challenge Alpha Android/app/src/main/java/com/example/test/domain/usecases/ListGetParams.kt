package com.example.test.domain.usecases

data class ListGetParams(
    val page: Int,
    val search: String? = null
)
