package br.com.mdr.starwars.di

import android.util.Log
import br.com.mdr.starwars.common.Constants.BASE_URL
import br.com.mdr.starwars.data.local.AppDatabase
import br.com.mdr.starwars.data.repository.RemoteDataSourceImpl
import br.com.mdr.starwars.domain.repository.RemoteDataSource
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

private const val DEFAULT_TIMEOUT = 15L

val networkModule = module {

    // Retrofit
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    // OkHttp Client
    single {
        OkHttpClient.Builder()
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    // Http Logging Interceptor
    single {
        HttpLoggingInterceptor {
            Timber.i(it)
            Log.i("OKHTTP", it)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    // Interceptor
    single {
        Interceptor { chain ->
            chain.request().run {
                newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-type", "application/json")
                    .build()
                    .let(chain::proceed)
            }
        }
    }

    // Remote DataSource
    single<RemoteDataSource> { RemoteDataSourceImpl(get(), get<AppDatabase>()) }
}
