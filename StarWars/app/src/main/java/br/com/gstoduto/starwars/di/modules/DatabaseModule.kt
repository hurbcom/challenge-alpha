package br.com.gstoduto.starwars.di.modules

import android.content.Context
import androidx.room.Room
import br.com.gstoduto.starwars.database.StarWarsDatabase
import br.com.gstoduto.starwars.database.dao.MovieDao
import br.com.gstoduto.starwars.database.dao.SpecieDao
import br.com.gstoduto.starwars.database.dao.VehicleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): StarWarsDatabase {
        return Room.databaseBuilder(
            context,
            StarWarsDatabase::class.java,
            "starwars.db"
        )  //.fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMovieDao(db: StarWarsDatabase): MovieDao {
        return db.movieDao()
    }

    @Provides
    fun provideVehicleDao(db: StarWarsDatabase): VehicleDao {
        return db.vehicleDao()
    }

    @Provides
    fun provideSpecieDao(db: StarWarsDatabase): SpecieDao {
        return db.specieDao()
    }
}