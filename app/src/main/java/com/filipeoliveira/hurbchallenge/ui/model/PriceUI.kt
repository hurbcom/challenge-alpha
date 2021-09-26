package com.filipeoliveira.hurbchallenge.ui.model

data class PriceUI(
    val currency: String,
    val pricePerDay: String
) {
    fun getPriceAsString(): String {
        return "$pricePerDay $currency"
    }
}