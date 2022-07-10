package com.edufelip.challengealpha.data.di

import com.edufelip.challengealpha.common.mapper.ListMapper
import com.edufelip.challengealpha.common.mapper.ListMapperImpl
import com.edufelip.challengealpha.common.mapper.PagedListMapper
import com.edufelip.challengealpha.data.mappers.base.MetaResponseToMeta
import com.edufelip.challengealpha.data.mappers.base.PagedListResponseToPagedListMapper
import com.edufelip.challengealpha.data.mappers.category_list.*
import com.edufelip.challengealpha.data.mappers.general_list.GeneralListMenuItemResponseToEntityMapper
import com.edufelip.challengealpha.data.network.models.*
import com.edufelip.challengealpha.domain.models.*
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

    @ViewModelScoped
    @Provides
    fun providesFilmListResponseToEntityMapper(): PagedListMapper<FilmResponse, Film> =
        PagedListResponseToPagedListMapper(
            metaMapper = MetaResponseToMeta(),
            listMapper = ListMapperImpl(
                mapper = FilmResponseToEntityMapper()
            ),
        )

    @ViewModelScoped
    @Provides
    fun providesPeopleListResponseToEntityMapper(): PagedListMapper<PeopleResponse, People> =
        PagedListResponseToPagedListMapper(
            metaMapper = MetaResponseToMeta(),
            listMapper = ListMapperImpl(
                mapper = PeopleResponseToEntityMapper()
            ),
        )

    @ViewModelScoped
    @Provides
    fun providesPlanetListResponseToEntityMapper(): PagedListMapper<PlanetResponse, Planet> =
        PagedListResponseToPagedListMapper(
            metaMapper = MetaResponseToMeta(),
            listMapper = ListMapperImpl(
                mapper = PlanetResponseToEntityMapper()
            ),
        )

    @ViewModelScoped
    @Provides
    fun providesSpecieListResponseToEntityMapper(): PagedListMapper<SpecieResponse, Specie> =
        PagedListResponseToPagedListMapper(
            metaMapper = MetaResponseToMeta(),
            listMapper = ListMapperImpl(
                mapper = SpecieResponseToEntityMapper()
            ),
        )

    @ViewModelScoped
    @Provides
    fun providesStarshipListResponseToEntityMapper(): PagedListMapper<StarshipResponse, Starship> =
        PagedListResponseToPagedListMapper(
            metaMapper = MetaResponseToMeta(),
            listMapper = ListMapperImpl(
                mapper = StarshipResponseToEntityMapper()
            ),
        )

    @ViewModelScoped
    @Provides
    fun providesVehicleListResponseToEntityMapper(): PagedListMapper<VehicleResponse, Vehicle> =
        PagedListResponseToPagedListMapper(
            metaMapper = MetaResponseToMeta(),
            listMapper = ListMapperImpl(
                mapper = VehicleResponseToEntityMapper()
            ),
        )
}