package com.hotelurbano.desafio.base.util

import java.text.NumberFormat
import java.util.*

/**
 * Created by Cristian on 17/12/2017.
 */
class CurrencyFormatter {
    companion object {
        fun getValue(value: Int): String {
            val numberFormat = NumberFormat.getCurrencyInstance(Locale("pt" , "BR"))
            numberFormat.maximumFractionDigits = 0
            return numberFormat.format(value)
        }
    }
}