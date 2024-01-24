package com.br.myapplication.di

import androidx.room.Room
import com.br.myapplication.data.dao.AppDatabase
import com.br.myapplication.data.dao.FilmsDao
import com.br.myapplication.data.repository.favorites.FavoritesRepository
import com.br.myapplication.data.repository.favorites.IFavoritesRepository
import com.br.myapplication.data.repository.film.FilmRepository
import com.br.myapplication.data.repository.film.IFilmRepository
import com.br.myapplication.data.repository.planet.IPlanetRepository
import com.br.myapplication.data.repository.planet.PlanetRepository
import com.br.myapplication.data.repository.specie.ISpeciesRepository
import com.br.myapplication.data.repository.specie.SpeciesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<IFilmRepository> {
        FilmRepository(
            apiService = get(),
            dao = get()
        )
    }

    single<IPlanetRepository> {
        PlanetRepository(
            apiService = get()
        )
    }

    single<ISpeciesRepository> {
        SpeciesRepository(
            apiService = get()
        )
    }

    single<IFavoritesRepository> {
        FavoritesRepository(
            filmsDao = get(),
            speciesDao = get(),
            planetsDao = get()
        )
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    single {
        get<AppDatabase>().filmDao()
    }

    single {
        get<AppDatabase>().speciesDao()
    }

    single {
        get<AppDatabase>().planetDao()
    }
}