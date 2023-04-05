package com.example.starwars

import android.app.Application
import com.example.starwars.modules.listFeature
import com.example.starwars.modules.retrofit
import com.example.starwars.modules.routes
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    private val modules = listOf(
        retrofit, routes, listFeature
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(modules)
        }
    }
}