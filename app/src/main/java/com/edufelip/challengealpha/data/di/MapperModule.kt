package com.edufelip.challengealpha.data.di

import com.edufelip.challengealpha.common.mapper.ListMapper
import com.edufelip.challengealpha.common.mapper.ListMapperImpl
import com.edufelip.challengealpha.data.mappers.GeneralListMenuItemResponseToEntityMapper
import com.edufelip.challengealpha.data.models.GeneralListMenuItemResponse
import com.edufelip.challengealpha.domain.models.GeneralListMenuItem
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MapperModule {
    @ViewModelScoped
    @Provides
    fun providesGeneralListMenuItemListMapper(): ListMapper<GeneralListMenuItemResponse, GeneralListMenuItem> =
        ListMapperImpl(
            mapper = GeneralListMenuItemResponseToEntityMapper()
        )
}