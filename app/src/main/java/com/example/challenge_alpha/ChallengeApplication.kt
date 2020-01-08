package com.example.challenge_alpha

import android.app.Application
import android.util.Log
import com.example.challenge_alpha.di.AppComponent
import com.example.challenge_alpha.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class ChallengeApplication : Application(), HasAndroidInjector {

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
}