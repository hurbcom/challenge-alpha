package com.br.myapplication.di

import com.br.myapplication.data.repository.film.FilmRepository
import com.br.myapplication.data.repository.film.IFilmRepository
import org.koin.dsl.module

val dataModule = module {

    single<IFilmRepository> {
        FilmRepository(
            apiService = get()
        )
    }

}