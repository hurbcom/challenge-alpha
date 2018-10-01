package com.example.belfortdev.modernandroid.list.data

import com.example.belfortdev.modernandroid.core.model.SearchDomain
import com.mpaani.core.networking.Outcome
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

interface ListDataContract {
    interface Repository {
        val hotelFetchOutcome: PublishSubject<Outcome<List<SearchDomain.Hotel>>>
        fun fetchHotels()
        fun refreshHotels()
        fun saveHotels(hotels: List<SearchDomain.Hotel?>)
        fun handleError(error: Throwable)
    }

    interface Local {
        fun getHotels(): Flowable<List<SearchDomain.Hotel>>
        fun saveHotels(hotels: List<SearchDomain.Hotel>)
    }

    interface Remote {
        fun getHotels(): Single<List<SearchDomain.Hotel?>?>
    }
}