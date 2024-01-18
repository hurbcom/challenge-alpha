package br.com.mdr.starwars.di

import androidx.room.Room
import br.com.mdr.starwars.data.local.AppDatabase
import br.com.mdr.starwars.data.repository.LocalDataSourceImpl
import br.com.mdr.starwars.domain.repository.LocalDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            context = androidApplication(),
            klass = AppDatabase::class.java,
            name = "app_database"
        ).build()
    }

    single<LocalDataSource> { LocalDataSourceImpl(get()) }
}