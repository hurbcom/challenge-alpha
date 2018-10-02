package com.belfortdev.hurbchallenge.list.injection

import com.belfortdev.hurbchallenge.core.data.local.HotelDatabase
import com.belfortdev.hurbchallenge.core.data.remote.SearchService
import com.belfortdev.hurbchallenge.core.injection.component.AppComponent
import com.belfortdev.hurbchallenge.core.network.Scheduler
import com.belfortdev.hurbchallenge.list.ui.ListActivity
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