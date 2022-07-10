package com.edufelip.challengealpha.data.di

import com.edufelip.challengealpha.common.mapper.ListMapper
import com.edufelip.challengealpha.data.data_sources.general_list.GeneralListMenuLocalDataSource
import com.edufelip.challengealpha.data.network.models.GeneralListMenuItemResponse
import com.edufelip.challengealpha.data.repositories.GeneralListMenuRepositoryImpl
import com.edufelip.challengealpha.domain.models.GeneralListMenuItem
import com.edufelip.challengealpha.domain.repositories.GeneralListMenuRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @ViewModelScoped
    @Provides
    fun providesGeneralListRepository(
        localDataSource: GeneralListMenuLocalDataSource,
        generalListMenuItemResponseToEntityMapper: ListMapper<GeneralListMenuItemResponse, GeneralListMenuItem>
    ): GeneralListMenuRepository = GeneralListMenuRepositoryImpl(
        localDataSource = localDataSource,
        generalListMenuItemResponseToEntityMapper = generalListMenuItemResponseToEntityMapper
    )
}