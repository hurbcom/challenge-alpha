package com.github.felipehjcosta.huchallenge.di

import com.github.felipehjcosta.huchallenge.base.di.ApplicationComponent
import com.github.felipehjcosta.huchallenge.base.hotels.HotelsModule
import com.github.felipehjcosta.huchallenge.feature.search.SearchUITest
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    TestApplicationModule::class,
    TestNetworkModule::class,
    HotelsModule::class,
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class
])
interface TestApplicationComponent : ApplicationComponent {
    fun inject(searchActivityTest: SearchUITest)
}