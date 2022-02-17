package com.isranascimento.lastviewed.di

import com.isranascimento.lastviewed.repository.ILastViewedRepository
import com.isranascimento.lastviewed.repository.LastViewedRepository
import com.isranascimento.lastviewed.ui.viewmodel.LastViewedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun lastViewedModule() = module {
    factory<ILastViewedRepository> { LastViewedRepository(get()) }
    viewModel { LastViewedViewModel(get()) }
}