package com.isranascimento.challengealpha

import android.app.Application
import com.isranascimento.hotelslist.di.hotelsModule
import com.isranascimento.network.di.networkModule
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                networkModule(BuildConfig.DEBUG, BASE_URL) +
                        hotelsModule()
            )
        }
    }

    companion object {
        private const val BASE_URL = "https://raw.githubusercontent.com/"
    }
}