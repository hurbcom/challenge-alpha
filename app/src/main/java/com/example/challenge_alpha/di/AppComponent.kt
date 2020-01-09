package com.example.challenge_alpha.di

import android.app.Application
import com.example.challenge_alpha.ChallengeApplication
import com.example.challenge_alpha.ui.resultDetail.ResultDetailViewModel
import com.example.challenge_alpha.ui.results.ResultsViewModel

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class, MainActivityModule::class, ViewModelAssistedFactoriesModule::class])
interface AppComponent {


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    val resultsViewModelFactory: ResultsViewModel.Factory
    val resultDetailViewModelFactory: ResultDetailViewModel.Factory

    fun inject(app: ChallengeApplication)

}


@AssistedModule
@Module(includes = [AssistedInject_ViewModelAssistedFactoriesModule::class])
abstract class ViewModelAssistedFactoriesModule
