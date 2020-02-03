package br.com.flyingdutchman.challenge_alpha.data.model

import com.google.gson.annotations.SerializedName

data class PriceX(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("amountPerDay")
    val amountPerDay: Double,
    @SerializedName("currentPrice")
    val currentPrice: Double,
    @SerializedName("old_price")
    val oldPrice: Double,
    @SerializedName("originalAmount")
    val originalAmount: Double,
    @SerializedName("sku")
    val sku: String
)