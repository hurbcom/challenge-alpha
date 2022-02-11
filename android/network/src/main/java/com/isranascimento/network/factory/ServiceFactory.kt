package com.isranascimento.network.factory

import android.util.Log
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.moczul.ok2curl.CurlInterceptor
import com.moczul.ok2curl.logger.Loggable
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceFactory(
    private val isDebug: Boolean,
    private val httpLoggingInterceptor: HttpLoggingInterceptor,
    private val curlInterceptor: CurlInterceptor
) {
    companion object {
        const val TIMEOUT_SECS = 25L
    }
    
    fun <Client> createRestClient(clazz: Class<Client>, url: HttpUrl): Client {
        val okHttpClientBuilder = OkHttpClient
            .Builder()
            .connectTimeout(TIMEOUT_SECS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECS, TimeUnit.SECONDS)

        if(isDebug) {
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
            okHttpClientBuilder.addInterceptor(curlInterceptor)
        }

        val retrofit = Retrofit
            .Builder()
            .baseUrl(url)
            .addConverterFactory(
                JacksonConverterFactory.create(
                    ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                )
            )
            .client(okHttpClientBuilder.build())
            .build()
        return retrofit.create(clazz)
    }
}