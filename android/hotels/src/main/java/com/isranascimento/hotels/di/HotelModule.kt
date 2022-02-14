package com.isranascimento.hotels.di

import com.isranascimento.hotels.repository.HotelsListRepository
import com.isranascimento.hotels.repository.IHotelsListRepository
import com.isranascimento.hotels.ui.viewmodels.HotelsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun hotelsModule() = module {
    factory<IHotelsListRepository> { HotelsListRepository(get()) }
    viewModel { HotelsListViewModel(get()) }
}