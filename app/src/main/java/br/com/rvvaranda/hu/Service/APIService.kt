package br.com.rvvaranda.hu.Service

import br.com.rvvaranda.hu.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object APIService {

    val baseUrl = BuildConfig.URI


    val apiInstance: APIClient = Retrofit.Builder().run {
        val gsonBuilder = GsonBuilder()
            .enableComplexMapKeySerialization()
            .setPrettyPrinting()
            .create()

        baseUrl(baseUrl)
        addConverterFactory(GsonConverterFactory.create(gsonBuilder))
        client(confgClient())
        build()
    }.create(APIClient::class.java)


    fun confgClient(): OkHttpClient {

        val interceptador = Interceptor{ chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .addInterceptor(interceptador)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build()
    }
}