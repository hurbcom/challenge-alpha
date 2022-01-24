package com.br.natanbrito.challenge.domain.model.filters

data class Price(
    val count: Int,
    val filter: String,
    val maxExclusive: Int,
    val min: Int
)