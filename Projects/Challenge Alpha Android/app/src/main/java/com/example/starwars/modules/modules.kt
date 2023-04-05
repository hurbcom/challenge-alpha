package com.example.starwars.modules

import com.example.starwars.BuildConfig.URL_API
import com.example.starwars.data.ApiRequest
import com.example.starwars.data.datasource.MoviesDatasource
import com.example.starwars.data.datasource.MoviesDatasourceImpl
import com.example.starwars.data.datasource.PeoplesDatasource
import com.example.starwars.data.datasource.PeoplesDatasourceImpl
import com.example.starwars.data.repository.MoviesRepository
import com.example.starwars.data.repository.MoviesRepositoryImpl
import com.example.starwars.data.repository.PeoplesRepository
import com.example.starwars.data.repository.PeoplesRepositoryImpl
import com.example.starwars.presentation.feature.listmovie.ListMovieViewModel
import com.example.starwars.presentation.adapter.Adapter
import com.example.starwars.presentation.feature.listpeople.ListPeopleViewModel
import com.example.starwars.retrofit.FlowCallAdapterFactory
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit = module {

    single { GsonBuilder().create() }

    single {

        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(get()))
            .baseUrl(URL_API)
            .addCallAdapterFactory(FlowCallAdapterFactory.create())
            .build()
            .create(ApiRequest::class.java)
    }
}

val routes = module {
    single<MoviesDatasource> {
        MoviesDatasourceImpl(get())
    }
    single<MoviesRepository> {
        MoviesRepositoryImpl(get())
    }
    single<PeoplesDatasource> {
        PeoplesDatasourceImpl(get())
    }
    single<PeoplesRepository> { PeoplesRepositoryImpl(get()) }
}
val listFeature = module {
    viewModel { ListPeopleViewModel(get()) }
    viewModel { ListMovieViewModel(get()) }
    factory { Adapter() }
}