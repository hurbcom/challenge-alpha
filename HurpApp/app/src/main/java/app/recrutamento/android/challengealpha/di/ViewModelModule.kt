package app.recrutamento.android.challengealpha.di

import app.recrutamento.android.challengealpha.ui.hotellist.HotelListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {

    viewModel {
        HotelListViewModel(get("repository"), androidApplication())
    }
}