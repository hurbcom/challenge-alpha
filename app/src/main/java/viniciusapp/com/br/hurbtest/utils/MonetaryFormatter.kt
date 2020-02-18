package viniciusapp.com.br.hurbtest.utils

import java.text.NumberFormat
import java.util.*

class MonetaryFormatter {

    companion object {
        private val ptBr = Locale("pt", "BR")
        fun format(value: Double?): String? {
            var value = value
            if (value == null) {
                value = 0.0
            }
            return NumberFormat.getCurrencyInstance(ptBr).format(value)
        }
    }
}