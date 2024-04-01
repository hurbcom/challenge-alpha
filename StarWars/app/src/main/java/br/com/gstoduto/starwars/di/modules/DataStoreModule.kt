package br.com.gstoduto.starwars.di.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import br.com.gstoduto.starwars.preferences.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Provides
    fun provideDatStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }
}