package br.com.flyingdutchman.challenge_alpha

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    companion object {
        lateinit var instance: App
            private set

        const val IO_SCHEDULER = "IO_SCHEDULER"
        const val MAIN_SCHEDULER = "MAIN_SCHEDULER"

    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@App)
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.INFO)
            modules(listOf(viewModelModule, repositoryModule, netModule, apiModule))
        }
    }
}