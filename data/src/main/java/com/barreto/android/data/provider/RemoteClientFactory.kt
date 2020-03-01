package com.barreto.android.data.provider

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okio.Buffer
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit.SECONDS
import kotlin.reflect.KClass

class RemoteClientFactory(
        baseUrl: String,
        debug: Boolean = false,
        interceptors: List<Interceptor> = emptyList(),
        authenticator: Authenticator? = null,
        connectTimeout: Long = 10,
        writeTimeout: Long = 10,
        readTimeout: Long = 30
) {
    private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    private val converterFactory = MoshiConverterFactory.create(moshi)

    private val logger = HttpLoggingInterceptor().setLevel(BODY)

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .apply { if (authenticator != null) authenticator(authenticator) }
            .apply { interceptors.forEach { addInterceptor(it) } }
            .apply { if (debug) addInterceptor(logger) }
            .connectTimeout(connectTimeout, SECONDS)
            .writeTimeout(writeTimeout, SECONDS)
            .readTimeout(readTimeout, SECONDS)
            .build()

    private val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    fun <T : Any> createClient(serviceClass: KClass<T>): T = retrofit.create(serviceClass.java)

    fun <E : Any> parseError(errorClass: KClass<E>, throwable: HttpException): E {
        return parseError(errorClass, throwable.response())
    }

    private fun <E : Any> parseError(errorClass: KClass<E>, response: Response<*>): E {
        val converter: Converter<ResponseBody, E> =
                retrofit.responseBodyConverter(errorClass.java, arrayOfNulls<Annotation>(0))
        return converter.convert(cloneErrorBody(response))
    }

    /**
     * This method is an adaptation from [okhttp3.Response.peekBody] to not consume response's
     * error body after read it.
     */
    private fun cloneErrorBody(response: Response<*>, byteCount: Long = 100_000): ResponseBody {
        val errorBody = response.errorBody()
                ?: throw IllegalArgumentException("The provided 'response' don't have an 'errorBody'")

        val source = errorBody.source()
        source.request(byteCount)
        val copy = source.buffer().clone()

        // There may be more than byteCount bytes in source.buffer(). If there is, return a prefix.
        val result: Buffer
        if (copy.size() > byteCount) {
            result = Buffer()
            result.write(copy, byteCount)
            copy.clear()
        } else {
            result = copy
        }

        return ResponseBody.create(errorBody.contentType(), result.size(), result)
    }
}
