package com.example.test.di

import com.example.test.domain.repositories.home.PeopleRepository
import com.example.test.domain.repositories.home.PlanetsRepository
import com.example.test.domain.repositories.home.StarshipsRepository
import com.example.test.domain.usecases.home.GetPeopleUseCase
import com.example.test.domain.usecases.home.GetPlanetsUseCase
import com.example.test.domain.usecases.home.GetStarshipsUseCase
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

    @Singleton
    @Provides
    fun providesGetPlanetsUseCase(planetsRepository: PlanetsRepository): GetPlanetsUseCase =
        GetPlanetsUseCase(planetsRepository)

    @Singleton
    @Provides
    fun providesGetStarshipsUseCase(starshipsRepository: StarshipsRepository): GetStarshipsUseCase =
        GetStarshipsUseCase(starshipsRepository)
}