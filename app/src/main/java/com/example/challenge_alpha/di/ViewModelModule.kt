package com.example.challenge_alpha.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge_alpha.ui.favorites.FavoritesViewModel
import com.example.challenge_alpha.ui.home.HomeViewModel
import com.example.challenge_alpha.ui.resultDetail.ResultDetailViewModel
import com.example.challenge_alpha.ui.results.ResultsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindFavoriteSetsViewModel(viewModel: FavoritesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ResultDetailViewModel::class)
    abstract fun bindResultDetailViewModel(viewModel: ResultDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ResultsViewModel::class)
    abstract fun bindResultsViewModel(viewModel: ResultsViewModel): ViewModel

    @Binds
    abstract fun provideViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

}