package com.vdemelo.starwarswiki.di

import com.vdemelo.starwarswiki.domain.usecase.HomeUseCase
import org.koin.dsl.module

val domainModule = module {
    single { HomeUseCase(get(), get()) }
}
