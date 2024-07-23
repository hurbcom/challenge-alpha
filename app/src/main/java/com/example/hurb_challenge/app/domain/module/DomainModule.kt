package com.example.hurb_challenge.app.domain.module

import com.example.hurb_challenge.app.domain.repository.CharactersRepository
import com.example.hurb_challenge.app.domain.repository.FilmsRepository
import com.example.hurb_challenge.app.domain.usecase.GetCharactersUseCase
import com.example.hurb_challenge.app.domain.usecase.GetFilmsUseCase
import com.example.hurb_challenge.app.domain.usecase.HomeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideGetFilmsUseCase(
        repository: FilmsRepository
    ): HomeUseCase.GetFilmsUseCase =
        GetFilmsUseCase(repository)

    @Provides
    fun provideGetCharactersUseCase(
        repository: CharactersRepository
    ): HomeUseCase.GetCharactersUseCase =
        GetCharactersUseCase(repository)
}
