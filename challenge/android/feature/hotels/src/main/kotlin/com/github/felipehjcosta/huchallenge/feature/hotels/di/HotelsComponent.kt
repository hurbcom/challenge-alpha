package com.github.felipehjcosta.huchallenge.feature.hotels.di

import com.github.felipehjcosta.huchallenge.base.di.AppInjector
import com.github.felipehjcosta.huchallenge.base.di.ApplicationComponent
import com.github.felipehjcosta.huchallenge.feature.hotels.HotelsActivity
import dagger.Component
import dagger.android.AndroidInjector

@HotelsScope
@Component(
        modules = [HotelsModule::class],
        dependencies = [ApplicationComponent::class]
)
interface HotelsComponent : AndroidInjector<HotelsActivity> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<HotelsActivity>() {
        abstract fun plus(component: ApplicationComponent): Builder
    }

}

fun setupDependencyInjection() {
    AppInjector.registerActivityBuilder(HotelsActivity::class.java) { applicationComponent ->
        DaggerHotelsComponent.builder().plus(applicationComponent)
    }
}