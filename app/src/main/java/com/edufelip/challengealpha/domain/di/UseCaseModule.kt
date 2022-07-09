package com.edufelip.challengealpha.domain.di

import com.edufelip.challengealpha.domain.repositories.GeneralListMenuRepository
import com.edufelip.challengealpha.domain.usecases.GetGeneralListMenuItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideGetGeneralListMenuItemsUseCase(
        repository: GeneralListMenuRepository
    ) = GetGeneralListMenuItemsUseCase(repository = repository)
}