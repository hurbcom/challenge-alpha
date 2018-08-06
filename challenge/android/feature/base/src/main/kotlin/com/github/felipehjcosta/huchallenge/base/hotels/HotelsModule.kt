package com.github.felipehjcosta.huchallenge.base.hotels

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HotelsModule {
    @Singleton
    @Provides
    fun providesHotelsRepository(searchApi: SearchApi): HotelsRepository {
        return NetworkHotelsRepository(searchApi)
    }
}