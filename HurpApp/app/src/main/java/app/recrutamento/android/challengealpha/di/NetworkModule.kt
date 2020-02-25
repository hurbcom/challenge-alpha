package app.recrutamento.android.challengealpha.di

import app.recrutamento.android.challengealpha.repository.api.HotelApiService
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber


val networkModule = module {
    val gson = GsonBuilder()
        .setLenient()
        .create()
    factory<Interceptor> {
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Timber.tag("OKHttp").d(it)
        })
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }

    factory { OkHttpClient.Builder().addInterceptor(get()).build() }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://www.hurb.com/search/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    factory { get<Retrofit>().create(HotelApiService::class.java) }

}