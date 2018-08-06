package com.github.felipehjcosta.huchallenge.feature.search.di

import com.github.felipehjcosta.huchallenge.base.di.AppInjector
import com.github.felipehjcosta.huchallenge.base.di.ApplicationComponent
import com.github.felipehjcosta.huchallenge.feature.search.SearchActivity
import dagger.Component
import dagger.android.AndroidInjector

@SearchScope
@Component(
        dependencies = [ApplicationComponent::class]
)
interface SearchComponent : AndroidInjector<SearchActivity> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<SearchActivity>() {
        abstract fun plus(component: ApplicationComponent): Builder
    }

}

fun setupDependencyInjection() {
    AppInjector.registerActivityBuilder(SearchActivity::class.java) { applicationComponent ->
        DaggerSearchComponent.builder().plus(applicationComponent)
    }
}