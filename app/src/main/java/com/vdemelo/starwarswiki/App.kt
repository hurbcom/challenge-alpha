package com.vdemelo.starwarswiki

import android.app.Application
import com.vdemelo.starwarswiki.di.appModule
import com.vdemelo.starwarswiki.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(androidContext = this@App)
            modules(
                appModule,
                networkModule
            )
        }
    }

}
