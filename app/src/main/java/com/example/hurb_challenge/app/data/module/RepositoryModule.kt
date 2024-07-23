package com.example.hurb_challenge.app.data.module

import com.example.hurb_challenge.app.data.ApiService
import com.example.hurb_challenge.app.data.repository.CharacterDetailsRepositoryImpl
import com.example.hurb_challenge.app.data.repository.CharactersRepositoryImpl
import com.example.hurb_challenge.app.data.repository.FilmDetailsRepositoryImpl
import com.example.hurb_challenge.app.data.repository.FilmsRepositoryImpl
import com.example.hurb_challenge.app.domain.repository.CharacterDetailsRepository
import com.example.hurb_challenge.app.domain.repository.CharactersRepository
import com.example.hurb_challenge.app.domain.repository.FilmDetailsRepository
import com.example.hurb_challenge.app.domain.repository.FilmsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCharactersRepository(
        apiService: ApiService
    ): CharactersRepository =
        CharactersRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideFilmsRepository(
        apiService: ApiService
    ): FilmsRepository =
        FilmsRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideCharacterDetailsRepository(
        apiService: ApiService
    ): CharacterDetailsRepository =
        CharacterDetailsRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideFilmDetailsRepository(
        apiService: ApiService
    ): FilmDetailsRepository =
        FilmDetailsRepositoryImpl(apiService)
}
