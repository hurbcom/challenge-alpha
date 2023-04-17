package br.com.hurbandroidchallenge.data.di

import br.com.hurbandroidchallenge.data.local.data_source.CharactersLocalDataSource
import br.com.hurbandroidchallenge.data.local.data_source.FilmsLocalDataSource
import br.com.hurbandroidchallenge.data.local.data_source.PlanetsLocalDataSource
import br.com.hurbandroidchallenge.data.remote.data_sources.StarWarsBookRemoteDataSource
import br.com.hurbandroidchallenge.data.remote.data_sources.StarWarsBookRemoteDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {

    single<StarWarsBookRemoteDataSource> { StarWarsBookRemoteDataSourceImpl(get()) }

    single { CharactersLocalDataSource(get()) }

    single { FilmsLocalDataSource(get()) }

    single { PlanetsLocalDataSource(get()) }

}