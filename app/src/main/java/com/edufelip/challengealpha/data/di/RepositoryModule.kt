package com.edufelip.challengealpha.data.di

import com.edufelip.challengealpha.common.mapper.ListMapper
import com.edufelip.challengealpha.common.mapper.PagedListMapper
import com.edufelip.challengealpha.data.data_sources.category_list.CategoryListRemoteDataSource
import com.edufelip.challengealpha.data.data_sources.general_list.GeneralListMenuLocalDataSource
import com.edufelip.challengealpha.data.network.models.*
import com.edufelip.challengealpha.data.repositories.CategoryListRepositoryImpl
import com.edufelip.challengealpha.data.repositories.GeneralListMenuRepositoryImpl
import com.edufelip.challengealpha.domain.models.*
import com.edufelip.challengealpha.domain.repositories.CategoryListRepository
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

    @ViewModelScoped
    @Provides
    fun providesCategoryListRepository(
        remoteDataSource: CategoryListRemoteDataSource,
        filmListResponseToEntityMapper: PagedListMapper<FilmResponse, Film>,
        peopleListResponseToEntityMapper: PagedListMapper<PeopleResponse, People>,
        planetListResponseToEntityMapper: PagedListMapper<PlanetResponse, Planet>,
        specieListResponseToEntityMapper: PagedListMapper<SpecieResponse, Specie>,
        starshipListResponseToEntityMapper: PagedListMapper<StarshipResponse, Starship>,
        vehicleListResponseToEntityMapper: PagedListMapper<VehicleResponse, Vehicle>,
    ): CategoryListRepository = CategoryListRepositoryImpl(
        remoteDataSource = remoteDataSource,
        filmListResponseToEntityMapper = filmListResponseToEntityMapper,
        peopleListResponseToEntityMapper = peopleListResponseToEntityMapper,
        planetListResponseToEntityMapper = planetListResponseToEntityMapper,
        specieListResponseToEntityMapper = specieListResponseToEntityMapper,
        starshipListResponseToEntityMapper = starshipListResponseToEntityMapper,
        vehicleListResponseToEntityMapper = vehicleListResponseToEntityMapper
    )
}