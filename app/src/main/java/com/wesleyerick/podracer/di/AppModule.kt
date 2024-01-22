package com.wesleyerick.podracer.di

import com.wesleyerick.podracer.data.api.getRetrofitInstance
import com.wesleyerick.podracer.data.api.getStarshipsService
import com.wesleyerick.podracer.data.api.getVehiclesService
import com.wesleyerick.podracer.data.repository.starships.IRepositoryStarships
import com.wesleyerick.podracer.data.repository.starships.StarshipsRepository
import com.wesleyerick.podracer.data.repository.vehicles.IRepositoryVehicles
import com.wesleyerick.podracer.data.repository.vehicles.VehiclesRepository
import com.wesleyerick.podracer.domain.usecase.starships.GetStarshipDetailsUseCase
import com.wesleyerick.podracer.domain.usecase.starships.GetStarshipsListUseCase
import com.wesleyerick.podracer.domain.usecase.starships.StarshipsUseCases
import com.wesleyerick.podracer.domain.usecase.vehicles.GetVehicleDetailsUseCase
import com.wesleyerick.podracer.domain.usecase.vehicles.GetVehiclesListUseCase
import com.wesleyerick.podracer.domain.usecase.vehicles.VehiclesUseCases
import com.wesleyerick.podracer.view.starships.StarshipsViewModel
import com.wesleyerick.podracer.view.vehicles.VehiclesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { VehiclesViewModel(get()) }
    viewModel { StarshipsViewModel(get()) }
}

val repositoryModule = module {
    single<IRepositoryVehicles> { VehiclesRepository(get()) }
    single<IRepositoryStarships> { StarshipsRepository(get()) }
}

val useCaseModule = module {
    factory { VehiclesUseCases(get(), get()) }
    factory { GetVehiclesListUseCase(get(), androidApplication()) }
    factory { GetVehicleDetailsUseCase(get(), androidApplication()) }

    factory { StarshipsUseCases(get(), get()) }
    factory { GetStarshipsListUseCase(get(), androidApplication()) }
    factory { GetStarshipDetailsUseCase(get(), androidApplication()) }
}

val apiModule = module {
    single { getRetrofitInstance() }
    factory { getVehiclesService(get()) }
    factory { getStarshipsService(get()) }
}
