package com.vdemelo.starwarswiki.di

import com.vdemelo.starwarswiki.data.repository.StarWarsLocalRepositoryImpl
import com.vdemelo.starwarswiki.data.repository.StarWarsRemoteRepositoryImpl
import com.vdemelo.starwarswiki.domain.usecase.HomeUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        HomeUseCase(
            remoteRepository = StarWarsRemoteRepositoryImpl(get()),
            localRepository = StarWarsLocalRepositoryImpl()
        )
    }
}
