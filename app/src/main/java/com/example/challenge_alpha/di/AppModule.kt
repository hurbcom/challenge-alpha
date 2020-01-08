package com.example.challenge_alpha.di

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge_alpha.ChallengeApplication
import com.example.challenge_alpha.db.favorites.FavoritesDataBase
import com.example.challenge_alpha.db.lastSeen.LastSeenDataBase
import com.example.challenge_alpha.db.resultDetail.ResultDetailDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module (includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideFavoritesDatabase(app: Application) = FavoritesDataBase.getInstance(app)

    @Singleton
    @Provides
    fun provideFavoritesDao(db: FavoritesDataBase) = db.favoritesDao()

    @Singleton
    @Provides
    fun provideLastSeenDatabase(app: Application) = LastSeenDataBase.getInstance(app)

    @Singleton
    @Provides
    fun provideLastSeenDao(db: LastSeenDataBase) = db.lastSeenDao()

    @Singleton
    @Provides
    fun provideResultDetailDatabase(app: Application) = ResultDetailDataBase.getInstance(app)

    @Singleton
    @Provides
    fun provideResultDetailDao(db: ResultDetailDataBase) = db.resultDetailDao()
}