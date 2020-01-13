package com.example.challenge_alpha

import android.app.Application
import com.example.challenge_alpha.di.AppComponent
import com.example.challenge_alpha.di.AppInjector
import com.example.challenge_alpha.di.DaggerAppComponent
import com.example.challenge_alpha.di.DaggerComponentProvider
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Configuração da aplicação para dependency injection (dagger2)
 */
open class ChallengeApplication : Application(), DaggerComponentProvider, HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        initializeComponent()
    }

    open fun initializeComponent() {
       AppInjector.init(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override val component: AppComponent
        get() = DaggerAppComponent
            .builder()
            .application(this)
            .build()


}