package com.vdemelo.starwarswiki.di

import com.vdemelo.starwarswiki.data.repository.StarWarsRemoteRepositoryImpl
import com.vdemelo.starwarswiki.domain.usecase.ItemsUseCase
import com.vdemelo.starwarswiki.domain.usecase.PlanetsUseCase
import com.vdemelo.starwarswiki.domain.usecase.SpeciesUseCase
import org.koin.dsl.module

val domainModule = module {
    single { SpeciesUseCase(remoteRepository = StarWarsRemoteRepositoryImpl(get())) }
    single { PlanetsUseCase(remoteRepository = StarWarsRemoteRepositoryImpl(get())) }
    single { ItemsUseCase() }
}
