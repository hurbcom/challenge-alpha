package com.example.core.base

import android.icu.text.NumberFormat
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.util.*

object Extensions {
    fun Int?.orZero(): Int = this ?: 0

    fun Int.withDots(): String = NumberFormat.getNumberInstance(Locale.getDefault()).format(this)

}