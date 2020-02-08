package br.com.rvvaranda.hu.Model


import com.google.gson.annotations.SerializedName

data class Taxe(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("amount_original")
    val amountOriginal: Double,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("currency_original")
    val currencyOriginal: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String
)