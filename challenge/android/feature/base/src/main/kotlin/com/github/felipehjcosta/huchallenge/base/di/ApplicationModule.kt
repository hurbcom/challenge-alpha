package com.github.felipehjcosta.huchallenge.base.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun providesApplicationContext(application: Application): Context = application
}