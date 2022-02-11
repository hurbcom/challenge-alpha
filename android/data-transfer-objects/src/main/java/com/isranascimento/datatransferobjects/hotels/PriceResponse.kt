package com.isranascimento.datatransferobjects.hotels

import com.fasterxml.jackson.annotation.JsonProperty

data class PriceResponse(
    @JsonProperty("currency") val currency: String?,
    @JsonProperty("currency_original") val currencyOriginal: String?,
    @JsonProperty("current_price") val currentPrice: Float?,
    @JsonProperty("old_price") val oldPrice: Float?,
    @JsonProperty("sku") val sku: String?,
    @JsonProperty("originalAmountPerDay") val originalAmountPerDay: Float?,
    @JsonProperty("amountPerDay") val amountPerDay: Float?,
    @JsonProperty("amount") val amount: Float?
)
