package com.edufelip.challengealpha.data.di

import com.edufelip.challengealpha.data.data_sources.general_list.GeneralListMenuLocalDataSource
import com.edufelip.challengealpha.data.data_sources.general_list.GeneralListMenuLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DataSourceModule {
    @ViewModelScoped
    @Provides
    fun providesGeneralListMenuLocalDataSource(): GeneralListMenuLocalDataSource =
        GeneralListMenuLocalDataSourceImpl()
}