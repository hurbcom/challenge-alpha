package com.isranascimento.database.di

import com.isranascimento.database.HotelsRoomDatabase
import com.isranascimento.database.IInsertHotelOnDatabase
import com.isranascimento.database.IRetrieveHotelList
import org.koin.dsl.binds
import org.koin.dsl.module

fun databaseModule() = module {
    single {
        HotelsRoomDatabase.getDatabase(get()).hotelsDao()
    } binds arrayOf(IInsertHotelOnDatabase::class, IRetrieveHotelList::class)
}