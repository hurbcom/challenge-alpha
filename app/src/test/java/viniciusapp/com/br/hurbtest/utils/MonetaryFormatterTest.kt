package viniciusapp.com.br.hurbtest.utils

import org.junit.Assert
import org.junit.Test

class MonetaryFormatterTest {

    @Test fun formatTest() {
        val testValue = 123.99
        val resultTest: String? = MonetaryFormatter.format(testValue)
        Assert.assertEquals("R$ 123,99", resultTest)

        val resultTestEmpty: String? = MonetaryFormatter.format(null)
        Assert.assertEquals("R$ 0,00", resultTestEmpty)
    }
}
