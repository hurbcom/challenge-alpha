package br.com.mdr.starwars

import android.app.Application
import br.com.mdr.starwars.di.apiModule
import br.com.mdr.starwars.di.databaseModule
import br.com.mdr.starwars.di.networkModule
import br.com.mdr.starwars.di.repositoryModule
import br.com.mdr.starwars.di.useCaseModule
import br.com.mdr.starwars.di.viewModelModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class StarWarsApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
        setupTimber()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@StarWarsApplication)
            modules(
                databaseModule,
                apiModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}