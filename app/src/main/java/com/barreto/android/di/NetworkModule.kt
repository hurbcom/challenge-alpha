package com.barreto.android.di

import com.barreto.android.data.provider.AuthInterceptor
import com.barreto.android.data.provider.HeadersInterceptor
import com.barreto.android.data.provider.Host
import com.barreto.android.data.provider.RemoteClientFactory
import com.barreto.android.domain.util.IClientPropertiesProvider
import com.barreto.android.BuildConfig
import com.barreto.android.domain.AppPropertiesProvider
import com.barreto.android.provider.AuthProvider
import okhttp3.Interceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val REMOTE_CLIENT_FACTORY = "RemoteClientFactory"

val networkModule = module {

    single(named(REMOTE_CLIENT_FACTORY)) {
        RemoteClientFactory(
            Host.fromBuildType(BuildConfig.BUILD_TYPE).baseUrl,
            BuildConfig.DEBUG,
            interceptors(get())
        )
    }

    single<IClientPropertiesProvider> { AppPropertiesProvider(androidContext()) }
}

private fun interceptors(clientProperties: IClientPropertiesProvider): List<Interceptor> {
    return listOf(
        AuthInterceptor(AuthProvider()),
        HeadersInterceptor(clientProperties)
    )
}



