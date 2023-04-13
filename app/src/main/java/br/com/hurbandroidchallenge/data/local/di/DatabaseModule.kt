package br.com.hurbandroidchallenge.data.local.di

import androidx.room.Room
import br.com.hurbandroidchallenge.data.local.database.StarWarsBookDatabase
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            context = get(),
            StarWarsBookDatabase::class.java,
            "starwarsdb"
        ).fallbackToDestructiveMigration().build()
    }

    single {
        get<StarWarsBookDatabase>().dao
    }
}