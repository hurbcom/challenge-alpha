package com.example.hurbandroidchallenge.data.di

import com.example.hurbandroidchallenge.data.remote.service.ServiceManager
import org.koin.android.BuildConfig
import org.koin.dsl.module

val apiModule = module {

    single { ServiceManager(BuildConfig.BASE_URL) }

}
