package com.example.belfortdev.modernandroid.list.injection

import android.arch.persistence.room.Room
import android.content.Context
import com.belfortdev.hurbchallenge.core.Constants
import com.belfortdev.hurbchallenge.core.data.remote.SearchService
import com.belfortdev.hurbchallenge.core.network.Scheduler
import com.belfortdev.hurbchallenge.list.data.ListDataContract
import com.example.belfortdev.modernandroid.core.data.local.HotelDatabase
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@Module
class ListModule {

    @Provides
    @ListScope
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @ListScope
    fun hotelDb(context: Context): HotelDatabase = Room.databaseBuilder(context, HotelDatabase::class.java, Constants.Hotels.DB_NAME).build()

    @Provides
    @ListScope
    fun searchService(retrofit: Retrofit): SearchService = retrofit.create(SearchService::class.java)
}