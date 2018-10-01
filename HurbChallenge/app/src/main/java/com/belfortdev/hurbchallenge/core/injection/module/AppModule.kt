package com.example.belfortdev.modernandroid.core.injection.module

import android.content.Context
import com.belfortdev.hurbchallenge.core.network.AppScheduler
import com.belfortdev.hurbchallenge.core.network.Scheduler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun scheduler(): Scheduler {
        return AppScheduler()
    }

}