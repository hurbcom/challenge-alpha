package com.barreto.android.di

import android.content.Context
import androidx.room.Room
import com.barreto.android.data.database.AppDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single { provideDatabase(androidContext()) }
}

fun provideDatabase(context: Context): AppDataBase {

    return Room.databaseBuilder(context, AppDataBase::class.java, "app-db")
//        .addMigrations(MIGRATION_1_2)
        .build()
}
