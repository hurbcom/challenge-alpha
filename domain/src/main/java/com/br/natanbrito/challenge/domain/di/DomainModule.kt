package com.br.natanbrito.challenge.domain.di

import com.br.natanbrito.challenge.domain.usecases.GetHotelsUseCase
import com.br.natanbrito.challenge.domain.usecases.GetHotelsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DomainModule {

    @Binds
    fun bindGetHotels(useCase: GetHotelsUseCaseImpl): GetHotelsUseCase

}