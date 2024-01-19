package com.br.myapplication

import android.app.Application
import com.br.myapplication.di.dataModule
import com.br.myapplication.di.retrofitModule
import com.br.myapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        configKoin()
    }

    private fun configKoin(){
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                dataModule,
                viewModelModule,
                retrofitModule
            )
        }
    }
}