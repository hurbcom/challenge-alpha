package com.hurb.challengealpha.util

import org.junit.Test

class UtilsTest {

    @Test
    fun testGetCurrencySymbol() {
        var currency = "BRL"
        assert(getCurrencySymbol(currency) == "R$")
        currency = "USD"
        assert(getCurrencySymbol(currency) == "$")
    }
}