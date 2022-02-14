package com.isranascimento.challengealpha

import android.app.Application
import com.isranascimento.challengealpha.di.KoinUtils
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                KoinUtils.getModules(BuildConfig.DEBUG, BASE_URL)
            )
        }
    }

    companion object {
        private const val BASE_URL = "https://raw.githubusercontent.com/"
    }
}