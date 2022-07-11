package com.edufelip.challengealpha.data.di

import com.edufelip.challengealpha.data.data_sources.category_list.CategoryListRemoteDataSource
import com.edufelip.challengealpha.data.data_sources.category_list.CategoryListRemoteDataSourceImpl
import com.edufelip.challengealpha.data.data_sources.favorites.FavoritesLocalDataSource
import com.edufelip.challengealpha.data.data_sources.favorites.FavoritesLocalDataSourceImpl
import com.edufelip.challengealpha.data.data_sources.general_list.GeneralListMenuLocalDataSource
import com.edufelip.challengealpha.data.data_sources.general_list.GeneralListMenuLocalDataSourceImpl
import com.edufelip.challengealpha.data.local.room.FavoriteDao
import com.edufelip.challengealpha.data.network.service.ApiService
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

    @ViewModelScoped
    @Provides
    fun providesCategoryListRemoteDataSource(apiService: ApiService): CategoryListRemoteDataSource =
        CategoryListRemoteDataSourceImpl(apiService)

    @ViewModelScoped
    @Provides
    fun providesFavoritesLocalDataSource(favoritesDao: FavoriteDao): FavoritesLocalDataSource =
        FavoritesLocalDataSourceImpl(favoritesDao)
}