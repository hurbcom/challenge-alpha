package com.isranascimento.challengealpha.di

import com.isranascimento.hotels.di.hotelsModule
import com.isranascimento.network.di.networkModule

object KoinUtils {
    fun getModules(isDebug: Boolean, baseUrl: String) =
        networkModule(isDebug, baseUrl) + hotelsModule()
}