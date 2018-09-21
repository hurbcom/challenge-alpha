package br.com.hu.allyson.desafiohu.domain

data class Price(
    val current_price: Double,
    val old_price: Double,
    val sku: String,
    val originalAmountPerDay: Double,
    val amountPerDay: Double,
    val amount: Double
)