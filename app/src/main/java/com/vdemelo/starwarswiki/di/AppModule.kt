package com.vdemelo.starwarswiki.di

import com.vdemelo.starwarswiki.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get()) }
}
