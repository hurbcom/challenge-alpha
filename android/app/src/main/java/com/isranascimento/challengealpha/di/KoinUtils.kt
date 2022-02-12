package com.isranascimento.challengealpha.di

import com.isranascimento.challengealpha.BuildConfig
import com.isranascimento.challengealpha.MainApplication
import com.isranascimento.hotelslist.di.hotelsModule
import com.isranascimento.network.di.networkModule

object KoinUtils {
    fun getModules(isDebug: Boolean, baseUrl: String) =
        networkModule(isDebug, baseUrl) + hotelsModule()
}