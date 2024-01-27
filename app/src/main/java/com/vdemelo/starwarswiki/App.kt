package com.vdemelo.starwarswiki

import android.app.Application
import com.vdemelo.starwarswiki.di.presentationModule
import com.vdemelo.starwarswiki.di.dataModule
import com.vdemelo.starwarswiki.di.domainModule
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
                dataModule,
                domainModule,
                presentationModule
            )
        }
    }

}
