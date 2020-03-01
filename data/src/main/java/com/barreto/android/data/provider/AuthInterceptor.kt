package com.barreto.android.data.provider

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
        private val authProvider: IAuthProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = if (authProvider.hasToken) {
            chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer ${authProvider.accessToken}")
                    .build()
        } else {
            chain.request()
        }

        return chain.proceed(request)
    }
}
