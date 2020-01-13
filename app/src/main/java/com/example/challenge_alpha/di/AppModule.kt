package com.example.challenge_alpha.di

import android.app.Application
import com.example.challenge_alpha.api.HurbService
import com.example.challenge_alpha.data.favorites.FavoritesDataBase
import com.example.challenge_alpha.data.lastSeen.LastSeenDataBase
import com.example.challenge_alpha.data.resultDetail.ResultDetailDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
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

    @Singleton
    @Provides
    fun provideRetroFit() = HurbService.create()
}