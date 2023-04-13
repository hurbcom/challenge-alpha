package br.com.hurbandroidchallenge.data.di

import br.com.hurbandroidchallenge.data.local.data_source.StarWarsBookLocalDataSource
import br.com.hurbandroidchallenge.data.local.data_source.StarWarsBookLocalDataSourceImpl
import br.com.hurbandroidchallenge.data.remote.data_sources.StarWarsBookRemoteDataSource
import br.com.hurbandroidchallenge.data.remote.data_sources.StarWarsBookRemoteDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {

    single<StarWarsBookRemoteDataSource> { StarWarsBookRemoteDataSourceImpl(get()) }

    single<StarWarsBookLocalDataSource> { StarWarsBookLocalDataSourceImpl(get()) }

}