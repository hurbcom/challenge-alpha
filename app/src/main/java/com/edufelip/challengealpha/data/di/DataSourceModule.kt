package com.edufelip.challengealpha.data.di

import com.edufelip.challengealpha.data.data_sources.general_list.GeneralListMenuLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideGeneralListMenuLocalDataSource() = GeneralListMenuLocalDataSourceImpl()
}