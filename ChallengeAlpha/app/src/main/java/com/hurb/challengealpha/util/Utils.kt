package com.hurb.challengealpha.util


//Returns brazilian currency symbol when currency is BRL, otherwise returns $
fun getCurrencySymbol(currency: String): String {
    if (currency == "BRL") {
        return "R$"
    } else if (currency == "USD") {
        return "$"
    }
    return "$"
}