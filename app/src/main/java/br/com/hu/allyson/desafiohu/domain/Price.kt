package br.com.hu.allyson.desafiohu.domain

data class Price(
    val currentPrice: Double,
    val oldPrice: Double,
    val sku: String,
    val originalAmountPerDay: Double,
    val amountPerDay: Double,
    val amount: Double
)