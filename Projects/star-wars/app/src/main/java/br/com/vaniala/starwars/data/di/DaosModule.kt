package br.com.vaniala.starwars.data.di

import br.com.vaniala.starwars.data.local.dao.CategoryDao
import br.com.vaniala.starwars.data.local.dao.CharacterDao
import br.com.vaniala.starwars.data.local.dao.FilmDao
import br.com.vaniala.starwars.data.local.dao.LastSeenDao
import br.com.vaniala.starwars.data.local.database.StarWarsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
@Module
@InstallIn(SingletonComponent::class)
class DaosModule {
    @Provides
    fun providesCategoryDao(
        db: StarWarsDatabase,
    ): CategoryDao = db.categoryDao()

    @Provides
    fun providesFilmDao(
        db: StarWarsDatabase,
    ): FilmDao = db.filmDao()

    @Provides
    fun providesCharacterDao(
        db: StarWarsDatabase,
    ): CharacterDao = db.characterDao()

    @Provides
    fun providesLastSeenDao(
        db: StarWarsDatabase,
    ): LastSeenDao = db.lastSeenDao()
}
