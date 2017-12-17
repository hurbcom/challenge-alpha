package com.hotelurbano.desafio.base.di.component

import android.app.Application
import android.content.res.Resources
import com.google.gson.Gson
import com.hotelurbano.desafio.api.Endpoints
import com.hotelurbano.desafio.base.di.module.ApiModule
import com.hotelurbano.desafio.base.di.module.AppModule
import com.hotelurbano.desafio.base.di.module.OkHttpModule
import com.hotelurbano.desafio.base.di.module.RetrofitModule
import com.hotelurbano.desafio.base.util.AppSchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Cristian on 17/12/2017.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, RetrofitModule::class, ApiModule::class, OkHttpModule::class))
interface AppComponent {
    fun application(): Application
    fun gson(): Gson
    fun resources(): Resources
    fun retrofit(): Retrofit
    fun endpoints(): Endpoints
    fun cache(): Cache
    fun client(): OkHttpClient
    fun loggingInterceptor(): HttpLoggingInterceptor
    fun compositeDisposable(): CompositeDisposable
    fun schedulerProvider(): AppSchedulerProvider
}