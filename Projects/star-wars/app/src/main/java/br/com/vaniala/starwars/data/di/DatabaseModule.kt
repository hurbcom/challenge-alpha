package br.com.vaniala.starwars.data.di

import android.content.Context
import androidx.room.Room
import br.com.vaniala.starwars.BuildConfig
import br.com.vaniala.starwars.data.local.database.StarWarsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Volatile
    private lateinit var db: StarWarsDatabase

    @Provides
    @Singleton
    fun instancia(@ApplicationContext context: Context): StarWarsDatabase {
        if (DatabaseModule::db.isInitialized) return db
        return Room.databaseBuilder(
            context,
            StarWarsDatabase::class.java,
            BuildConfig.DATABASE_NAME,
        ).build()
            .also {
                db = it
            }
    }
}
