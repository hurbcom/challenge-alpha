package br.com.hu.allyson.desafiohu.domain

import android.os.Parcel
import android.os.Parcelable

data class Price(
    val currentPrice: Double,
    val oldPrice: Double,
    val sku: String,
    val originalAmountPerDay: Double,
    val amountPerDay: Double,
    val amount: Double
)