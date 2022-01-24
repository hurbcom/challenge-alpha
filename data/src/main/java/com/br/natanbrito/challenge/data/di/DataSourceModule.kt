package com.br.natanbrito.challenge.data.di

import com.br.natanbrito.challenge.data.datasource.HotelDataSource
import com.br.natanbrito.challenge.data.datasource.HotelDataSourceImpl
import com.br.natanbrito.challenge.data.repository.HotelRepositoryImpl
import com.br.natanbrito.challenge.data.repository.HotelsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindHotelRepository(
        hotelRepository: HotelRepositoryImpl
    ): HotelsRepository

    @Singleton
    @Binds
    abstract fun bindHotelDataSource(hotelDataSourceImpl: HotelDataSourceImpl): HotelDataSource

}