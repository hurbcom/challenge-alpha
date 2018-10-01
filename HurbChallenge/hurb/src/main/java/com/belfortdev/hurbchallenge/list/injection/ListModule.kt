package com.belfortdev.hurbchallenge.list.injection

import android.arch.persistence.room.Room
import android.content.Context
import com.belfortdev.hurbchallenge.core.Constants
import com.belfortdev.hurbchallenge.core.data.local.HotelDatabase
import com.belfortdev.hurbchallenge.core.data.remote.SearchService
import com.belfortdev.hurbchallenge.core.network.Scheduler
import com.belfortdev.hurbchallenge.list.ListAdapter
import com.belfortdev.hurbchallenge.list.data.ListDataContract
import com.belfortdev.hurbchallenge.list.data.ListLocalData
import com.belfortdev.hurbchallenge.list.data.ListRemoteData
import com.belfortdev.hurbchallenge.list.data.ListRepository
import com.belfortdev.hurbchallenge.list.viewmodel.ListViewModelFactory
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@Module
class ListModule {

    @Provides
    @ListScope
    fun adapter(picasso: Picasso): ListAdapter = ListAdapter(picasso)

    @Provides
    @ListScope
    fun listViewModelFactory(repository: ListDataContract.Repository, compositeDisposable: CompositeDisposable): ListViewModelFactory = ListViewModelFactory(repository, compositeDisposable)

    @Provides
    @ListScope
    fun listRepo(local: ListDataContract.Local, remote: ListDataContract.Remote, scheduler: Scheduler, compositeDisposable: CompositeDisposable): ListDataContract.Repository = ListRepository(local, remote, scheduler, compositeDisposable)

    @Provides
    @ListScope
    fun remoteData(searchService: SearchService): ListDataContract.Remote = ListRemoteData(searchService)

    @Provides
    @ListScope
    fun localData(hotelDb: HotelDatabase, scheduler: Scheduler): ListDataContract.Local = ListLocalData(hotelDb, scheduler)

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