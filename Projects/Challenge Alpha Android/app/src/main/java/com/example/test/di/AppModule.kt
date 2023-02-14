package com.example.test.di

import com.example.core.base.Extensions.md5
import com.example.test.BuildConfig
import com.example.test.data.datasources.network.MockApi
import com.example.test.data.datasources.network.api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesHttpInterceptor(): Interceptor {
        return object : Interceptor {

            override fun intercept(chain: Interceptor.Chain): Response {
                val original: Request = chain.request()
                val originalHttpUrl: HttpUrl = original.url

                val ts = Date().time.toString()
                val hash =
                    "$ts${BuildConfig.API_PRIVATE_KEY}${BuildConfig.API_PUBLIC_KEY}".md5()

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apikey", BuildConfig.API_PUBLIC_KEY)
                    .addQueryParameter("hash", hash)
                    .addQueryParameter("ts", ts)
                    .build()

                val requestBuilder: Request.Builder = original.newBuilder()
                    .url(url)
                val request: Request = requestBuilder.build()
                return chain.proceed(request)
            }
        }
    }

    @Singleton
    @Provides
    fun providesHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(BuildConfig.API_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(BuildConfig.API_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Singleton
    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient, moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun providesApi(retrofit: Retrofit): api {
        return if (BuildConfig.BUILD_TYPE.contentEquals("mock")) MockApi()
        else retrofit.create(api::class.java)
    }
}