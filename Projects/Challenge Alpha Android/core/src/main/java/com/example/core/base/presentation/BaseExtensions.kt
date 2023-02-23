package com.example.core.base.presentation

import android.os.Build
import android.os.Bundle
import java.io.Serializable
import java.util.*

object BaseExtensions {

    fun String.capitalizeWords(): String = split(" ").joinToString(" ") {
        it.lowercase().replaceFirstChar { char -> char.titlecase() }
    }

    inline fun <reified T : Serializable> Bundle.serializable(key: String): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializable(key, T::class.java)
        else -> @Suppress("DEPRECATION") getSerializable(key) as? T
    }
}