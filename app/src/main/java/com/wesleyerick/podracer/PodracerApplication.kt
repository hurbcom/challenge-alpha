package com.wesleyerick.podracer

import android.app.Application
import com.wesleyerick.podracer.di.apiModule
import com.wesleyerick.podracer.di.repositoryModule
import com.wesleyerick.podracer.di.useCaseModule
import com.wesleyerick.podracer.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class PodracerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PodracerApplication)
            loadKoinModules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    useCaseModule,
                    apiModule,
                ),
            )
        }
    }
}