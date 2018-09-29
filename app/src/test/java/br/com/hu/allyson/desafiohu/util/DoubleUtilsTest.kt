package br.com.hu.allyson.desafiohu.util
import org.junit.Test
import org.junit.Assert.*

class DoubleUtilsTest {

    @Test
    fun formatToBrazilianCurrencySucesso() {

        var value = DoubleUtils.formatToBrazilianCurrency(100.00)

        assertEquals("R$ 100,00", value)

    }
}