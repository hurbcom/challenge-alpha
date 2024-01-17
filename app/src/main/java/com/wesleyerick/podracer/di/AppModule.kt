package com.wesleyerick.podracer.di

import com.wesleyerick.podracer.data.api.getRetrofitInstance
import com.wesleyerick.podracer.data.api.getVehiclesService
import com.wesleyerick.podracer.data.repository.IRepositoryVehicles
import com.wesleyerick.podracer.data.repository.VehiclesRepository
import com.wesleyerick.podracer.domain.usecase.vehicles.GetAllVehiclesUseCase
import com.wesleyerick.podracer.domain.usecase.vehicles.GetVehicleDetailsUseCase
import com.wesleyerick.podracer.domain.usecase.vehicles.VehiclesUseCases
import com.wesleyerick.podracer.view.vehicles.VehiclesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { VehiclesViewModel(get()) }
}

val repositoryModule = module {
    single<IRepositoryVehicles> { VehiclesRepository(get()) }
}

val useCaseModule = module {
    factory { VehiclesUseCases(get(), get()) }
    factory { GetAllVehiclesUseCase(get(), androidApplication()) }
    factory { GetVehicleDetailsUseCase(get(), androidApplication()) }
}

val apiModule = module {
    single { getRetrofitInstance() }
    factory { getVehiclesService(get()) }
}
