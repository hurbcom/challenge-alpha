package com.barreto.android.di

import com.barreto.android.domain.util.ISchedulerProvider
import com.barreto.android.domain.AppSchedulerProvider
import org.koin.dsl.module


const val USER_TOKEN = "userToken"

val appModule = module {

    single<ISchedulerProvider> { AppSchedulerProvider() }

}