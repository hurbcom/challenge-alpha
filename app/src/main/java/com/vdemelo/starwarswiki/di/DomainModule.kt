package com.vdemelo.starwarswiki.di

import com.vdemelo.starwarswiki.data.repository.StarWarsRemoteRepositoryImpl
import com.vdemelo.starwarswiki.domain.usecase.SpeciesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        SpeciesUseCase(
            remoteRepository = StarWarsRemoteRepositoryImpl(get())
        )
    }
}
