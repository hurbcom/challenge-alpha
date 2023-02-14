package com.example.test.di

import com.example.test.domain.repositories.Repository
import com.example.test.domain.usecases.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun providesGetHeroesUseCase(repository: Repository): UseCase =
        UseCase(repository)
}