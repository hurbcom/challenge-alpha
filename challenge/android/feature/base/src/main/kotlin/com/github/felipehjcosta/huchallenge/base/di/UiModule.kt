package com.github.felipehjcosta.huchallenge.base.di

import android.content.Context
import com.github.felipehjcosta.huchallenge.base.imageloader.ImageLoader
import com.github.felipehjcosta.huchallenge.base.imageloader.UniversalImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UiModule {
    @Singleton
    @Provides
    fun providesImageLoader(context: Context): ImageLoader {
        return UniversalImageLoader(createUniversalImageLoader(context))
    }

    private fun createUniversalImageLoader(context: Context): com.nostra13.universalimageloader.core.ImageLoader {
        val config = ImageLoaderConfiguration.Builder(context).build()
        return com.nostra13.universalimageloader.core.ImageLoader.getInstance().apply {
            init(config)
        }
    }
}