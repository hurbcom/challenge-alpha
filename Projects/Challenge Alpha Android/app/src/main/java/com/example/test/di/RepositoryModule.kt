package com.example.test.di

import com.example.test.data.datasources.network.api
import com.example.test.data.repositories.RepositoryImpl
import com.example.test.domain.repositories.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providesRepository(api: api): Repository = RepositoryImpl(api)

}