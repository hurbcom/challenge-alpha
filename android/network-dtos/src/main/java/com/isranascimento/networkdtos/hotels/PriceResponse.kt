package com.isranascimento.networkdtos.hotels

import com.fasterxml.jackson.annotation.JsonProperty

data class PriceResponse(
    @JsonProperty("currency") val currency: String,
    @JsonProperty("currency_original") val currencyOriginal: String,
    @JsonProperty("current_price") val currentPrice: Double,
    @JsonProperty("old_price") val oldPrice: Double,
    @JsonProperty("sku") val sku: String,
    @JsonProperty("originalAmountPerDay") val originalAmountPerDay: Double,
    @JsonProperty("amountPerDay") val amountPerDay: Double,
    @JsonProperty("amount") val amount: Double
)
