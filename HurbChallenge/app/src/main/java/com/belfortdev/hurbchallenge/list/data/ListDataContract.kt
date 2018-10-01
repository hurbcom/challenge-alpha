package com.belfortdev.hurbchallenge.list.data

import com.belfortdev.hurbchallenge.core.model.SearchDomain
import com.belfortdev.hurbchallenge.core.network.Outcome
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