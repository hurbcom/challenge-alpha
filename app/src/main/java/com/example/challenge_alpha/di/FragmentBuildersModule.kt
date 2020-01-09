package com.example.challenge_alpha.di

import com.example.challenge_alpha.ui.favorites.FavoritesFragment
import com.example.challenge_alpha.ui.home.HomeFragment
import com.example.challenge_alpha.ui.resultDetail.ResultDetailFragment
import com.example.challenge_alpha.ui.results.ResultsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {


    @ContributesAndroidInjector
    abstract fun contributeFavoritesFragment(): FavoritesFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeResultDetailFragment(): ResultDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeResultsFragment(): ResultsFragment


}