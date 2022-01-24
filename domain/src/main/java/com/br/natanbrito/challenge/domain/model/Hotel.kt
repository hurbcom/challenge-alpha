package com.br.natanbrito.challenge.domain.model

import com.br.natanbrito.challenge.domain.model.results.Result
import com.br.natanbrito.challenge.domain.model.filters.Filters

data class Hotel(
    val filters: Filters?,
    val results: List<Result>?
)