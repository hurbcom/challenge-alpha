package com.filipeoliveira.hurbchallenge.ui.model

data class PriceUI(
    val currency: String,
    val pricePerDay: String
) {
    fun getParsedPrice(): String {
        return "$pricePerDay $currency"
    }
}