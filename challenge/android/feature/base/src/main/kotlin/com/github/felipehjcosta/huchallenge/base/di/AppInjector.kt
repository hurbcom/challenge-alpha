package com.github.felipehjcosta.huchallenge.base.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.github.felipehjcosta.huchallenge.base.MainApplication
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector

object AppInjector {

    private val activityMap = mutableMapOf<Class<out Activity>, (ApplicationComponent) -> AndroidInjector.Builder<out Activity>>()

    internal fun init(demoApplication: MainApplication) {
        demoApplication.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks by EmptyActivityLifecycleCallbacks() {
            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                handleActivity(activity)
            }
        })
    }

    private fun handleActivity(activity: Activity?) {
        if (activity is FragmentActivity) {
            try {
                AndroidInjection.inject(activity)
            } catch (e: IllegalArgumentException) {
                android.util.Log.i("AppInjector", "Unable to inject activity: ${activity.javaClass.simpleName}", e)
            }
        }
    }

    fun <T : Activity> registerActivityBuilder(clazz: Class<T>, block: (ApplicationComponent) -> AndroidInjector.Builder<T>) {
        activityMap[clazz] = block
    }

    internal fun decorateActivityAndroidInjector(androidInjector: AndroidInjector<Activity>, component: ApplicationComponent): AndroidInjector<Activity> {
        return InstantAppsActivityInjector(component, activityMap, androidInjector)
    }
}