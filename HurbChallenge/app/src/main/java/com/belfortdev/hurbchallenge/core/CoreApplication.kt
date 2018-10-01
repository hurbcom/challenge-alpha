package com.belfortdev.hurbchallenge.core

import android.app.Application
import com.example.belfortdev.modernandroid.core.injection.component.AppComponent
import com.example.belfortdev.modernandroid.core.injection.component.DaggerAppComponent
import com.example.belfortdev.modernandroid.core.injection.module.AppModule
import com.karntrehan.posts.core.networking.synk.Synk

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