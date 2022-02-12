package com.isranascimento.challengealpha

import android.app.Application
import com.isranascimento.challengealpha.di.KoinUtils
import com.isranascimento.hotelslist.di.hotelsModule
import com.isranascimento.network.di.networkModule
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                KoinUtils.getModules()
            )
        }
    }

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/"
    }
}