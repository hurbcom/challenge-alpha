package com.filipeoliveira.hurbchallenge

import androidx.room.Room
import com.filipeoliveira.hurbchallenge.data.HotelRepositoryImpl
import com.filipeoliveira.hurbchallenge.data.local.Database
import com.filipeoliveira.hurbchallenge.data.local.HotelLocalDataSourceImpl
import com.filipeoliveira.hurbchallenge.data.remote.HotelRemoteDataSourceImpl
import com.filipeoliveira.hurbchallenge.ui.favorite.HotelFavoriteListViewModel
import com.filipeoliveira.hurbchallenge.ui.list.HotelListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    single<HotelRemoteDataSourceImpl> {
        HotelRemoteDataSourceImpl(
            assetManager = androidContext().assets
        )
    }
    single<HotelLocalDataSourceImpl> {
        HotelLocalDataSourceImpl(
            database = Room.databaseBuilder(
                androidContext(),
                Database::class.java,
                "Database"
            ).build()
        )
    }
    single<HotelRepositoryImpl> {
        HotelRepositoryImpl(
            remoteDataSource = get<HotelRemoteDataSourceImpl>(),
            localDataSource = get<HotelLocalDataSourceImpl>()
        )
    }

    viewModel {
        HotelListViewModel(
            repository = get<HotelRepositoryImpl>()
        )
    }

    viewModel {
        HotelFavoriteListViewModel(
            repository = get<HotelRepositoryImpl>()
        )
    }
}