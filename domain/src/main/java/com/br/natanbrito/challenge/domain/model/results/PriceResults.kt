package com.br.natanbrito.challenge.domain.model.results

data class PriceResults(
    val amount: Double,
    val amountPerDay: Double,
    val currency: String,
    val currency_original: String,
    val current_price: Double,
    val old_price: Double,
    val originalAmountPerDay: Double,
    val sku: String
)