package br.com.vaniala.starwars.data.di

import android.content.Context
import br.com.vaniala.starwars.core.StatusConnectivity
import br.com.vaniala.starwars.data.local.dao.CategoryDao
import br.com.vaniala.starwars.data.local.dao.CharacterDao
import br.com.vaniala.starwars.data.local.dao.FilmDao
import br.com.vaniala.starwars.data.local.datasource.LocalCategoryDataSourceImpl
import br.com.vaniala.starwars.data.local.datasource.LocalCharacterDataSourceImpl
import br.com.vaniala.starwars.data.local.datasource.LocalDataSource
import br.com.vaniala.starwars.data.local.datasource.LocalFilmDataSourceImpl
import br.com.vaniala.starwars.data.remote.datasource.RemoteDataSource
import br.com.vaniala.starwars.data.remote.datasource.RemoteDataSourceImpl
import br.com.vaniala.starwars.data.remote.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
@Module
@InstallIn(SingletonComponent::class)
object ProvidesModule {

    @Singleton
    @Provides
    fun providesRemoteDataSource(
        service: ApiService,
    ): RemoteDataSource {
        return RemoteDataSourceImpl(service)
    }

    @Singleton
    @Provides
    fun providesLocalCategoryDataSource(
        categoryDao: CategoryDao,
    ): LocalDataSource.Categories {
        return LocalCategoryDataSourceImpl(categoryDao)
    }

    @Singleton
    @Provides
    fun providesLocalFilmDataSource(
        filmDao: FilmDao,
    ): LocalDataSource.Films {
        return LocalFilmDataSourceImpl(filmDao)
    }

    @Singleton
    @Provides
    fun providesLocalCharacterDataSource(
        characterDao: CharacterDao,
    ): LocalDataSource.Characters {
        return LocalCharacterDataSourceImpl(characterDao)
    }

    @Singleton
    @Provides
    fun providesLocalStatusConnectivity(
        @ApplicationContext context: Context,
    ) = StatusConnectivity(context)
}
