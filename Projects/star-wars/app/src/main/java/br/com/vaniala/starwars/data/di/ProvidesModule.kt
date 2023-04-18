package br.com.vaniala.starwars.data.di

import android.content.Context
import br.com.vaniala.starwars.core.StatusConnectivity
import br.com.vaniala.starwars.data.local.dao.CategoryDao
import br.com.vaniala.starwars.data.local.dao.CharacterDao
import br.com.vaniala.starwars.data.local.dao.FilmDao
import br.com.vaniala.starwars.data.local.dao.LastSeenDao
import br.com.vaniala.starwars.data.local.datasource.LocalCategoryDataSourceImpl
import br.com.vaniala.starwars.data.local.datasource.LocalCharacterDataSourceImpl
import br.com.vaniala.starwars.data.local.datasource.LocalDataSource
import br.com.vaniala.starwars.data.local.datasource.LocalFavoritesDataSourceImpl
import br.com.vaniala.starwars.data.local.datasource.LocalFilmDataSourceImpl
import br.com.vaniala.starwars.data.local.datasource.LocalLastSeenDataSourceImpl
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
    fun providesLocalFavoritesDataSource(
        filmDao: FilmDao,
        characterDao: CharacterDao,
    ): LocalDataSource.Favorites {
        return LocalFavoritesDataSourceImpl(filmDao, characterDao)
    }

    @Singleton
    @Provides
    fun providesLocalLastSeenDataSource(
        lastSeenDao: LastSeenDao,
    ): LocalDataSource.LastSeenI {
        return LocalLastSeenDataSourceImpl(lastSeenDao)
    }

    @Singleton
    @Provides
    fun providesLocalStatusConnectivity(
        @ApplicationContext context: Context,
    ) = StatusConnectivity(context)
}
