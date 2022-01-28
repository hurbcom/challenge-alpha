package com.br.natanbrito.challenge.data.model.results

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PriceResults(
    val amount: Double,
    val amountPerDay: Double,
    val currency: String,
    @SerializedName("currency_original")
    val currencyOriginal: String,
    @SerializedName("current_price")
    val currentPrice: Double,
    @SerializedName("old_price")
    val oldPrice: Double,
    val originalAmountPerDay: Double,
    val sku: String
): Parcelable
