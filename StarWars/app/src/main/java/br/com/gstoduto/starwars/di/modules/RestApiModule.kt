package br.com.gstoduto.starwars.di.modules

import br.com.gstoduto.starwars.network.services.MovieService
import br.com.gstoduto.starwars.network.services.SpecieService
import br.com.gstoduto.starwars.network.services.VehicleService
import br.com.gstoduto.starwars.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RestApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Provides
    @Singleton
    fun provideVehicleService(retrofit: Retrofit): VehicleService {
        return retrofit.create(VehicleService::class.java)
    }

    @Provides
    @Singleton
    fun provideSpecieService(retrofit: Retrofit): SpecieService {
        return retrofit.create(SpecieService::class.java)
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS) // Tempo limite de conexão de 30 segundos
            .readTimeout(30, TimeUnit.SECONDS) // Tempo limite de leitura de 30 segundos
            .writeTimeout(30, TimeUnit.SECONDS) // Tempo limite de escrita de 30 segundos
            .build()
    }
}
