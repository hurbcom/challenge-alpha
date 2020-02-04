package br.com.flyingdutchman.challenge_alpha.di

import android.app.Application
import android.content.Context
import br.com.flyingdutchman.challenge_alpha.App.Companion.IO_SCHEDULER
import br.com.flyingdutchman.challenge_alpha.App.Companion.MAIN_SCHEDULER
import br.com.flyingdutchman.challenge_alpha.BuildConfig
import br.com.flyingdutchman.challenge_alpha.data.HurbRepository
import br.com.flyingdutchman.challenge_alpha.data.network.HurbApi
import br.com.flyingdutchman.challenge_alpha.data.network.ResultRemoteEntityMapper
import br.com.flyingdutchman.challenge_alpha.ui.search.SearchViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val viewModelModule = module {

    viewModel {
        SearchViewModel(
            get(),
            get(),
            get(named(MAIN_SCHEDULER))
        )
    }
}


val apiModule = module {
    fun provideOfferApi(retrofit: Retrofit): HurbApi {
        return retrofit.create(HurbApi::class.java)
    }

    single { provideOfferApi(get()) }
}

val netModule = module {
    fun provideTimeout(): Long = 30

    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideHttpClient(
        cache: Cache,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        cacheInterceptor: Interceptor
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(cacheInterceptor)
            .cache(cache)
            .connectTimeout(provideTimeout(), TimeUnit.SECONDS)
            .readTimeout(provideTimeout(), TimeUnit.SECONDS)
            .writeTimeout(provideTimeout(), TimeUnit.SECONDS)

        return okHttpClientBuilder.build()
    }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }


    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.REST_SERVER)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }


    fun provideHttpLogging(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        when (BuildConfig.DEBUG) {
            true -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
    )


    fun provideCacheControlInterceptor(context: Context) = Interceptor { chain ->

        val request = chain.request()
        val response = chain.proceed(request)

        if (response.cacheResponse() != null && response.cacheControl().isPublic) {
            response

        } else {
            response
                .newBuilder()
                .headers(response.headers())
                .build()
        }
    }

    single { provideHttpLogging() }
    single { provideCacheControlInterceptor(androidContext()) }
    single { provideCache(androidApplication()) }
    single { provideHttpClient(get(), get(), get()) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }

}


val repositoryModule = module {

    fun provideRemoteMapper(): ResultRemoteEntityMapper {
        return ResultRemoteEntityMapper()
    }


    fun provideRepository(
        api: HurbApi,
        remoteMapper: ResultRemoteEntityMapper,
        ioScheduler: Scheduler
    ): HurbRepository {
        return HurbRepository(api, remoteMapper, ioScheduler)
    }

    single { provideRemoteMapper() }
    single { provideRepository(get(), get(), get(named(IO_SCHEDULER))) }
    factory { CompositeDisposable() }
    single(named(IO_SCHEDULER)) { Schedulers.io() }
    single(named(MAIN_SCHEDULER)) { AndroidSchedulers.mainThread() }
}


