package com.belfortdev.hurbchallenge.core

import android.app.Application
import com.belfortdev.hurbchallenge.core.injection.component.AppComponent
import com.belfortdev.hurbchallenge.core.injection.component.DaggerAppComponent
import com.belfortdev.hurbchallenge.core.injection.module.AppModule
import com.belfortdev.hurbchallenge.core.network.synk.Synk

open class CoreApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initSynk()
        setupDI()
    }

    private fun initSynk() {
        Synk.init(this)
    }

    private fun setupDI() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}