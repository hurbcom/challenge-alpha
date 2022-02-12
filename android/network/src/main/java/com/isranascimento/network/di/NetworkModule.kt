package com.isranascimento.network.di

import android.util.Log
import com.isranascimento.network.factory.ServiceFactory
import com.isranascimento.network.restclient.HurbRestClient
import com.isranascimento.network.service.HurbApiService
import com.isranascimento.network.service.IHotelsRemoteDataSource
import com.moczul.ok2curl.CurlInterceptor
import com.moczul.ok2curl.logger.Loggable
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.binds
import org.koin.dsl.module

fun networkModule(isDebug: Boolean, baseUrl: String) = module {
    single { provideHttpLoggingInterceptor() }
    single { provideLoggable() }
    single { CurlInterceptor(get()) }
    single { ServiceFactory(isDebug, get(), get()) }
    single { provideRestClient(get(), baseUrl) }
    factory {
        HurbApiService(get())
    } binds arrayOf(IHotelsRemoteDataSource::class)
}

private fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
    this.level = HttpLoggingInterceptor.Level.BODY
}

private fun provideLoggable() = Loggable { message -> Log.d("CURL", message) }

private fun provideRestClient(factory: ServiceFactory, baseUrl: String): HurbRestClient {
    return factory.createRestClient(HurbRestClient::class.java, baseUrl.toHttpUrl())
}