package br.com.mdr.starwars.di

import br.com.mdr.starwars.presentation.categories.CategoriesViewModel
import br.com.mdr.starwars.presentation.films.FilmsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CategoriesViewModel(get()) }
    viewModel { FilmsViewModel(get()) }
}