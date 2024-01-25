package com.vdemelo.starwarswiki.di

import com.vdemelo.starwarswiki.ui.HomeViewModel
import com.vdemelo.starwarswiki.ui.species.SpeciesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { SpeciesViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}
