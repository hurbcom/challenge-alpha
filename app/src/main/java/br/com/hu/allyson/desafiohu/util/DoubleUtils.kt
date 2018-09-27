package br.com.hu.allyson.desafiohu.util

import java.text.DecimalFormat
import java.util.*

object DoubleUtils {

    fun formatToBrazilianCurrency(value: Double):String{

        return DecimalFormat.getCurrencyInstance(Locale("pt", "br")).format(value)

    }

}