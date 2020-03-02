package com.hurb.challengealpha.util


fun getCurrencySymbol(currency: String): String {
    if (currency == "BRL") {
        return "R$"
    } else if (currency == "USD") {
        return "$"
    }
    return "$"
}