package br.com.gstoduto.starwars.ui.use_case.movie

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DomainModule {
    @Binds
    fun bindGetMoviesUseCase(repositoryImpl: GetMoviesUseCaseImpl): GetMoviesUseCase
}