package com.hotelurbano.desafio.base

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.hotelurbano.desafio.base.di.component.AppComponent
import com.hotelurbano.desafio.base.di.component.DaggerAppComponent
import com.hotelurbano.desafio.base.di.module.AppModule

/**
 * Created by Cristian on 17/12/2017.
 */
class App : Application() {

    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
        Fresco.initialize(this)
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}