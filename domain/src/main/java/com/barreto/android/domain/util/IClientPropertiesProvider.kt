package com.barreto.android.domain.util

interface IClientPropertiesProvider {

    val userAgent: String

    val device: Map<String, String>
}
