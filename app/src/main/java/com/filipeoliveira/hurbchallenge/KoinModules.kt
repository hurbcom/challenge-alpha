package com.filipeoliveira.hurbchallenge

import com.filipeoliveira.hurbchallenge.data.HotelRepositoryImpl
import com.filipeoliveira.hurbchallenge.data.remote.HotelDataSourceImpl
import com.filipeoliveira.hurbchallenge.ui.list.HotelListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    single <HotelDataSourceImpl>{ HotelDataSourceImpl(
        assetManager = androidContext().assets
    ) }
    single <HotelRepositoryImpl>{ HotelRepositoryImpl(
        remoteDataSource = get<HotelDataSourceImpl>()
    ) }

    viewModel { HotelListViewModel(
        repository = get<HotelRepositoryImpl>()
    ) }
}