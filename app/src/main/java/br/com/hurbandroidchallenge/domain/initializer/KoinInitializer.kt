package br.com.hurbandroidchallenge.domain.initializer

import android.content.Context
import androidx.startup.Initializer
import br.com.hurbandroidchallenge.data.di.dataSourceModule
import br.com.hurbandroidchallenge.data.di.mapperModule
import br.com.hurbandroidchallenge.data.di.repositoryModule
import br.com.hurbandroidchallenge.data.local.di.databaseModule
import br.com.hurbandroidchallenge.data.remote.di.apiModule
import br.com.hurbandroidchallenge.domain.di.useCaseModule
import br.com.hurbandroidchallenge.domain.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class KoinInitializer : Initializer<KoinApplication> {
    override fun create(context: Context): KoinApplication {
        return startKoin {
            androidContext(context)
            modules(
                apiModule,
                dataSourceModule,
                mapperModule,
                viewModelModule,
                useCaseModule,
                repositoryModule,
                databaseModule
            )
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}