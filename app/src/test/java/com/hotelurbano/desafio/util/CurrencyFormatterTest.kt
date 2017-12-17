package com.hotelurbano.desafio.util

import com.hotelurbano.desafio.base.util.CurrencyFormatter
import org.junit.Assert
import org.junit.Test

/**
 * Created by Cristian on 17/12/2017.
 */
class CurrencyFormatterTest {

    @Test
    fun getValue_should_returnCurrencyFormatter() {
        val result = CurrencyFormatter.getValue(300)
        Assert.assertEquals("R$ 300", result)
    }
}