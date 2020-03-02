package com.hurb.challengealpha.model

import com.google.gson.annotations.SerializedName

data class Price(

    @SerializedName("amount") val amount: Double,
    @SerializedName("old_price", alternate = ["oldPrice"]) val old_price: Double,
    @SerializedName("currency") val currency: String,
    @SerializedName("currency_original") val currency_original: String,
    @SerializedName("gain") val gain: Int,
    @SerializedName("fee_extra_original") val fee_extra_original: Int,
    @SerializedName("gain_original") val gain_original: Int,
    @SerializedName("tariff_policies") val tariff_policies: List<String>,
    @SerializedName("current_price", alternate = ["currentPrice"]) val current_price: Double,
    @SerializedName("total_price") val total_price: Double,
    @SerializedName("fee_extra") val fee_extra: Int,
    @SerializedName("sku") val sku: String,
    @SerializedName("taxes") val taxes: List<Tax>,
    @SerializedName("originalAmountPerDay") val originalAmountPerDay: Double,
    @SerializedName("amountPerDay") val amountPerDay: Double
)