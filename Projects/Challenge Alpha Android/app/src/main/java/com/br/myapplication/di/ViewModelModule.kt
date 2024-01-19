package com.br.myapplication.di

import com.br.myapplication.ui.film.FilmsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        FilmsViewModel(
            filmRepository = get()
        )
    }
}