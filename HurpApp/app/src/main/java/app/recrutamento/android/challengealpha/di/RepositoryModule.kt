package app.recrutamento.android.challengealpha.di

import app.recrutamento.android.challengealpha.repository.HotelRepository
import app.recrutamento.android.challengealpha.repository.remotedata.HotelRemoteDataSource
import org.koin.dsl.module.module

val repositoryModule = module {
    single  ("api"){ HotelRemoteDataSource(get()) }
    single("repository") { HotelRepository(get("api"))}
}