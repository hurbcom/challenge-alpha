package com.edufelip.challengealpha.domain.di

import com.edufelip.challengealpha.domain.repositories.GeneralListMenuRepository
import com.edufelip.challengealpha.domain.usecases.GetGeneralListMenuItemsUseCase
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
}