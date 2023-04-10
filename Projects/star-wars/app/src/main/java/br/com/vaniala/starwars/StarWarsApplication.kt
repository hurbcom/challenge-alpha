package br.com.vaniala.starwars

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
@HiltAndroidApp
class StarWarsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
