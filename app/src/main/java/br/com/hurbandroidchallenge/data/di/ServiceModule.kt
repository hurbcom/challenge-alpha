package br.com.hurbandroidchallenge.data.di

import br.com.hurbandroidchallenge.data.remote.service.ServiceManager
import br.com.hurbandroidchallenge.data.remote.config.ApiConfig
import org.koin.dsl.module

val apiModule = module {

    single { ServiceManager(ApiConfig.baseUrl) }

}
