package com.barreto.android

import android.app.Application
import com.barreto.android.di.appModule
import com.barreto.android.di.networkModule
import com.barreto.android.presentation.content.contentModule
//import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ProjectApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@ProjectApplication)
            modules(
                listOf(
                    appModule,
                    networkModule,
                    contentModule
                )
            )
        }
    }
}
