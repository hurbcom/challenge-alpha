package com.br.natanbrito.challenge.data.model

import com.br.natanbrito.challenge.data.model.results.Result
import com.br.natanbrito.challenge.data.model.filters.Filters

data class Hotel(
    val filters: Filters?,
    val results: List<Result>?
)