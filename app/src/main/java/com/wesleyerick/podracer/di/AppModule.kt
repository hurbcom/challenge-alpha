package com.wesleyerick.podracer.di

import com.wesleyerick.podracer.data.api.getRetrofitInstance
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
//    viewModel { GenericViewModel(get()) }
}

val repositoryModule = module {
//    single<IRepositoryGeneric> { GenericRepository(get()) }
}

val useCaseModule = module {
//    factory { GetGenericUseCase(get(), androidApplication()) }
}

val apiModule = module {
    single { getRetrofitInstance() }
//    factory { getGenericService(get()) }
}