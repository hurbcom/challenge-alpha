package com.example.core.base

import java.io.UnsupportedEncodingException
import java.security.MessageDigest

object Extensions {
    fun Int?.orZero(): Int = this ?: 0
}