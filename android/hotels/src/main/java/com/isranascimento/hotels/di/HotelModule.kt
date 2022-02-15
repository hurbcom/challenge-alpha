package com.isranascimento.hotels.di

import com.isranascimento.database.HotelsRoomDatabase
import com.isranascimento.hotels.repository.HotelsDetailRepository
import com.isranascimento.hotels.repository.HotelsListRepository
import com.isranascimento.hotels.repository.IHotelsDetailRepository
import com.isranascimento.hotels.repository.IHotelsListRepository
import com.isranascimento.hotels.ui.viewmodels.HotelsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun hotelsModule() = module {
    factory<IHotelsListRepository> { HotelsListRepository(get()) }
    viewModel { HotelsListViewModel(get()) }
    single<IHotelsDetailRepository> {
        HotelsDetailRepository(get())
    }
    single {
        HotelsRoomDatabase.getDatabase(get()).hotelsDao()
    }
}