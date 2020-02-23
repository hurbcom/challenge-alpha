package app.recrutamento.android.challengealpha
import android.app.Application
import app.recrutamento.android.challengealpha.di.networkModule
import app.recrutamento.android.challengealpha.di.repositoryModule
import app.recrutamento.android.challengealpha.di.viewModelModule
import org.koin.android.ext.android.startKoin

class HotelApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        configureDI()
    }

    fun configureDI() {
        startKoin(this, provideComponent())
    }

    fun provideComponent() = listOf(networkModule, repositoryModule, viewModelModule)

}