package com.github.felipehjcosta.huchallenge.base

import android.app.Activity
import android.app.Application
import com.github.felipehjcosta.huchallenge.base.di.AppInjector
import com.github.felipehjcosta.huchallenge.base.di.ApplicationComponent
import com.github.felipehjcosta.huchallenge.base.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

open class MainApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingActivityAndroidInjector: DispatchingAndroidInjector<Activity>

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = createComponent()
        component.inject(this)
        AppInjector.init(this)
    }

    protected open fun createComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder().application(this).build()
    }

    override fun activityInjector(): AndroidInjector<Activity> =
            AppInjector.decorateActivityAndroidInjector(dispatchingActivityAndroidInjector, component)


}