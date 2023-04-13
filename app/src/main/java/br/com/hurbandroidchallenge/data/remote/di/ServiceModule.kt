package br.com.hurbandroidchallenge.data.remote.di

import br.com.hurbandroidchallenge.data.remote.config.ApiUrls
import br.com.hurbandroidchallenge.data.remote.service.ServiceManager
import br.com.hurbandroidchallenge.data.remote.service.StarWarsBookService
import org.koin.dsl.module

val apiModule = module {

    single { ServiceManager(ApiUrls.baseUrl) }

    single { get<ServiceManager>().create(StarWarsBookService::class.java) }

}
