package com.example.belfortdev.modernandroid.list.injection

import com.belfortdev.hurbchallenge.list.ListActivity
import com.belfortdev.hurbchallenge.core.data.remote.SearchService
import com.belfortdev.hurbchallenge.core.network.Scheduler
import com.example.belfortdev.modernandroid.core.data.local.HotelDatabase
import com.example.belfortdev.modernandroid.core.injection.component.AppComponent
import com.squareup.picasso.Picasso
import dagger.Component

@ListScope
@Component(dependencies = [AppComponent::class], modules = [ListModule::class])
interface ListComponent {

    //Expose to dependent components
    fun hotelDb(): HotelDatabase

    fun searchService(): SearchService
    fun picasso(): Picasso
    fun scheduler(): Scheduler

    fun inject(listActivity: ListActivity)
}