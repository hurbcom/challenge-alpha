package com.filipeoliveira.hurbchallenge.ui.model

import java.io.Serializable

data class PriceUI(
    val currency: String,
    val pricePerDay: String
): Serializable {
    fun getPriceAsString(): String {
        return "$pricePerDay $currency"
    }
}