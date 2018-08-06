package com.github.felipehjcosta.huchallenge.base.hotels

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Price(
        @SerializedName("amount") val amount: BigDecimal = BigDecimal.ZERO
)