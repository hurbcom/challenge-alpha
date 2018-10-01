package com.example.belfortdev.modernandroid.core.injection.component

import android.content.Context
import android.content.SharedPreferences
import com.belfortdev.hurbchallenge.core.network.Scheduler
import com.example.belfortdev.modernandroid.core.injection.module.AppModule
import com.example.belfortdev.modernandroid.core.injection.module.ImageModule
import com.example.belfortdev.modernandroid.core.injection.module.NetworkModule
import com.example.belfortdev.modernandroid.core.injection.module.StorageModule
import com.squareup.picasso.Picasso
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, StorageModule::class, ImageModule::class])
interface AppComponent {

    fun context(): Context

    fun retrofit(): Retrofit

    fun picasso(): Picasso

    fun sharedPreferences(): SharedPreferences

    fun scheduler(): Scheduler

}