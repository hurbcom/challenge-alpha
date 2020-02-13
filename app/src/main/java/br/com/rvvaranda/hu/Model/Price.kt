package br.com.rvvaranda.hu.Model


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("amountPerDay")
    val amountPerDay: Double,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("currency_original")
    val currencyOriginal: String,
    @SerializedName("current_price")
    val currentPrice: Double,
    @SerializedName("fee_extra")
    val feeExtra: Int,
    @SerializedName("fee_extra_original")
    val feeExtraOriginal: Int,
    @SerializedName("gain")
    val gain: Int,
    @SerializedName("gain_original")
    val gainOriginal: Int,
    @SerializedName("old_price")
    val oldPrice: Double,
    @SerializedName("originalAmountPerDay")
    val originalAmountPerDay: Double,
    @SerializedName("sku")
    val sku: String,
    @SerializedName("tariff_policies")
    val tariffPolicies: ArrayList<Any>,
    @SerializedName("taxes")
    val taxes: ArrayList<Taxe>,
    @SerializedName("total_price")
    val totalPrice: Double
)