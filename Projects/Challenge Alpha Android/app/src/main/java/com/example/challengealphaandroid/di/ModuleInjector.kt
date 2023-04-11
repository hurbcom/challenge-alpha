package com.example.challengealphaandroid.di

import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.example.challengealphaandroid.BuildConfig
import com.example.challengealphaandroid.api.IApolloClient
import com.example.challengealphaandroid.api.RealApolloClient
import com.example.challengealphaandroid.data.*
import com.example.challengealphaandroid.data.room.*
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleInjector {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideRealApolloClient(apolloClient: ApolloClient): IApolloClient {
        return RealApolloClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(realApolloClient: IApolloClient): RemoteApolloRepository {
        return RemoteApolloRepository(realApolloClient)
    }

    @Provides
    @Singleton
    fun providePersonAppDatabase(@ApplicationContext appContext: Context): LocalRoom {
        return LocalRoom.getInstance(appContext)
    }

    @Provides
    fun providePersonDao(appDatabase: LocalRoom): PersonDao {
        return appDatabase.personDao()
    }

    @Provides
    fun provideStarshipDao(appDatabase: LocalRoom): StarshipDao {
        return appDatabase.starshipDao()
    }

    @Provides
    fun providePlanetDao(appDatabase: LocalRoom): PlanetDao {
        return appDatabase.planetDao()
    }

    @Provides
    @Singleton
    fun provideLocalPersonRepository(personDao: PersonDao): LocalPersonRepository {
        return LocalPersonRepositoryImpl(personDao)
    }

    @Provides
    @Singleton
    fun provideLocalStarshipRepository(starshipDao: StarshipDao): LocalStarshipRepository {
        return LocalStarshipRepositoryImpl(starshipDao)
    }

    @Provides
    @Singleton
    fun provideLocalPlanetRepository(planetDao: PlanetDao): LocalPlanetRepository {
        return LocalPlanetRepositoryImpl(planetDao)
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_REST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideRestRepository(retrofit: Retrofit): RemoteRestRepository {
        return RemoteRestRepositoryImpl(retrofit)
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    // .header("Authorization", "Bearer PLACE_YOUR_TOKEN")
                    .method(original.method, original.body)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    fun moshi(): Moshi {
        return Moshi.Builder()
            .build()
    }

    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor;
    }

}