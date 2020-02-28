package com.hurb.challengealpha.model

import com.google.gson.annotations.SerializedName

data class Tax(

    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String,
    @SerializedName("amount") val amount: Double,
    @SerializedName("amount_original") val amount_original: Double,
    @SerializedName("currency") val currency: String,
    @SerializedName("currency_original") val currency_original: String
)