package com.example.test.di

import com.example.test.domain.repositories.home.PeopleRepository
import com.example.test.domain.usecases.home.GetPeopleUseCase
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
    fun providesGetPeopleUseCase(peopleRepository: PeopleRepository): GetPeopleUseCase =
        GetPeopleUseCase(peopleRepository)
}