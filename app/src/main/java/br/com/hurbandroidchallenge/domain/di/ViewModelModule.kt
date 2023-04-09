package br.com.hurbandroidchallenge.domain.di

import br.com.hurbandroidchallenge.presentation.screens.home.HomeListViewModel
import org.koin.dsl.module

val viewModelModule = module {

    single {
        HomeListViewModel(
            getHomeCategoriesUseCase = get()
        )
    }

}