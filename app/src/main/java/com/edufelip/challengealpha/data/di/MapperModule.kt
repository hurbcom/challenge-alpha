package com.edufelip.challengealpha.data.di

import com.edufelip.challengealpha.common.mapper.ListMapperImpl
import com.edufelip.challengealpha.data.mappers.GeneralListMenuItemResponseToEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
object MapperModule {
    @Singleton
    @Provides
    fun provideGeneralListMenuItemListMapper() = ListMapperImpl(
        mapper = GeneralListMenuItemResponseToEntityMapper()
    )
}