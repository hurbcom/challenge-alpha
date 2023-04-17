package br.com.vaniala.starwars.data.di

import br.com.vaniala.starwars.data.repository.CategoryRepositoryImpl
import br.com.vaniala.starwars.data.repository.CharacterRepositoryImpl
import br.com.vaniala.starwars.data.repository.FavoritesRepositoryImpl
import br.com.vaniala.starwars.data.repository.FilmRepositoryImpl
import br.com.vaniala.starwars.domain.repository.CategoryRepository
import br.com.vaniala.starwars.domain.repository.CharacterRepository
import br.com.vaniala.starwars.domain.repository.FavoritesRepository
import br.com.vaniala.starwars.domain.repository.FilmRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
@Module
@InstallIn(SingletonComponent::class)
interface BindsModule {

    @Binds
    @Singleton
    fun bindsCategoryRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl,
    ): CategoryRepository

    @Binds
    @Singleton
    fun bindsFilmRepository(
        ilmRepositoryImpl: FilmRepositoryImpl,
    ): FilmRepository

    @Binds
    @Singleton
    fun bindsCharacterRepository(
        characterRepository: CharacterRepositoryImpl,
    ): CharacterRepository

    @Binds
    @Singleton
    fun bindsFavoritesRepository(
        characterRepository: FavoritesRepositoryImpl,
    ): FavoritesRepository
}
