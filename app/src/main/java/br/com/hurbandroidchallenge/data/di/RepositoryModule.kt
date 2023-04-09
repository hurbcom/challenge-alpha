package br.com.hurbandroidchallenge.data.di

import br.com.hurbandroidchallenge.data.mapper.HomeCategoriesResponseToEntityMapper
import br.com.hurbandroidchallenge.data.remote.data_sources.StarWarsBookRemoteDataSourceImpl
import br.com.hurbandroidchallenge.data.repository.StarWarsBookRepositoryImpl
import br.com.hurbandroidchallenge.domain.repository.StarWarsBookRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<StarWarsBookRepository> {
        StarWarsBookRepositoryImpl(
            remoteDataSource = StarWarsBookRemoteDataSourceImpl(get()),
            homeCategoriesResponseToEntityMapper = get<HomeCategoriesResponseToEntityMapper>()
        )
    }

}