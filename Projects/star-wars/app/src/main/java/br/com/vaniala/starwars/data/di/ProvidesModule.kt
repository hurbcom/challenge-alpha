package br.com.vaniala.starwars.data.di

import br.com.vaniala.starwars.data.remote.RemoteDataSource
import br.com.vaniala.starwars.data.remote.RemoteDataSourceImpl
import br.com.vaniala.starwars.data.remote.service.ApiService
import dagger.Module
import dagger.Provides
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
object ProvidesModule {

    @Singleton
    @Provides
    fun providesRemoteDataSource(
        service: ApiService,
    ): RemoteDataSource {
        return RemoteDataSourceImpl(service)
    }
}
