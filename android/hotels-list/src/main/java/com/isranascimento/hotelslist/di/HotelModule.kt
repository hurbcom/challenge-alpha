package com.isranascimento.hotelslist.di

import com.isranascimento.hotelslist.repository.HotelsListRepository
import com.isranascimento.hotelslist.repository.IHotelsListRepository
import com.isranascimento.hotelslist.ui.viewmodels.HotelsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun hotelsModule() = module {
    factory<IHotelsListRepository> { HotelsListRepository(get()) }
    viewModel { HotelsListViewModel(get()) }
}