package com.edufelip.challengealpha.domain.di

import com.edufelip.challengealpha.domain.repositories.CategoryListRepository
import com.edufelip.challengealpha.domain.repositories.GeneralListMenuRepository
import com.edufelip.challengealpha.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @ViewModelScoped
    @Provides
    fun providesGetGeneralListMenuItemsUseCase(
        repository: GeneralListMenuRepository
    ): GetGeneralListMenuItemsUseCase = GetGeneralListMenuItemsUseCase(repository = repository)

    @ViewModelScoped
    @Provides
    fun providesGetFilmListUseCase(
        repository: CategoryListRepository
    ): GetFilmListUseCase = GetFilmListUseCase(repository = repository)

    @ViewModelScoped
    @Provides
    fun providesGetPeopleListUseCase(
        repository: CategoryListRepository
    ): GetPeopleListUseCase = GetPeopleListUseCase(repository = repository)

    @ViewModelScoped
    @Provides
    fun providesGetPlanetListUseCase(
        repository: CategoryListRepository
    ): GetPlanetListUseCase = GetPlanetListUseCase(repository = repository)

    @ViewModelScoped
    @Provides
    fun providesGetSpecieListUseCase(
        repository: CategoryListRepository
    ): GetSpecieListUseCase = GetSpecieListUseCase(repository = repository)

    @ViewModelScoped
    @Provides
    fun providesGetStarshipListUseCase(
        repository: CategoryListRepository
    ): GetStarshipListUseCase = GetStarshipListUseCase(repository = repository)

    @ViewModelScoped
    @Provides
    fun providesGetVehicleListUseCase(
        repository: CategoryListRepository
    ): GetVehicleListUseCase = GetVehicleListUseCase(repository = repository)
}