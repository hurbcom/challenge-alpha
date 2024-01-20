package br.com.mdr.starwars.di

import br.com.mdr.starwars.data.remote.api.StarWarsApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { get<Retrofit>().create(StarWarsApi::class.java) }
}
