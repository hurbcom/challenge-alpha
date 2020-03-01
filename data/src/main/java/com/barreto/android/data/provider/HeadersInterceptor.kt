package com.barreto.android.data.provider

import com.barreto.android.domain.util.IClientPropertiesProvider
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HeadersInterceptor(
        private val headersProvider: IClientPropertiesProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response? {
        val originalRequest = chain.request()

        val headersBuilder = originalRequest.headers().newBuilder()

        headersBuilder.addIfNotPresent("User-Agent", headersProvider.userAgent)

        val requestBuilder = originalRequest.newBuilder()
        requestBuilder.headers(headersBuilder.build())

        var response: Response? = null
        var exception: IOException? = null
        var tryCount = 0
        val maxTryCount = 3

        do {
            try {
                response = chain.proceed(requestBuilder.build())
            } catch (error: IOException) {
                exception = error
            } finally {
                tryCount++
            }
        } while (tryCount <= maxTryCount && (response == null || !response.isSuccessful))

        if (response == null && exception != null) throw exception

        return response
    }
}
