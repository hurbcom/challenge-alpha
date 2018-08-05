package com.github.felipehjcosta.huchallenge.base.di

import android.app.Application
import com.github.felipehjcosta.huchallenge.base.MainApplication
import com.github.felipehjcosta.huchallenge.base.hotels.HotelsModule
import com.github.felipehjcosta.huchallenge.base.hotels.HotelsRepository
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    NetworkModule::class,
    HotelsModule::class
])
interface ApplicationComponent {
    fun inject(application: MainApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    val hotelsRepository: HotelsRepository
}