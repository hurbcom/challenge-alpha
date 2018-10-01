package com.example.belfortdev.modernandroid.list.data

import com.belfortdev.hurbchallenge.core.network.Scheduler
import com.belfortdev.hurbchallenge.list.HotelException
import com.example.belfortdev.modernandroid.core.extension.*
import com.example.belfortdev.modernandroid.core.model.SearchDomain
import com.karntrehan.posts.core.networking.synk.Synk
import com.karntrehan.posts.core.networking.synk.SynkKeys
import com.mpaani.core.networking.Outcome
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit


class ListRepository(
        private val local: ListDataContract.Local,
        private val remote: ListDataContract.Remote,
        private val scheduler: Scheduler,
        private val compositeDisposable: CompositeDisposable
) : ListDataContract.Repository {

    override val hotelFetchOutcome: PublishSubject<Outcome<List<SearchDomain.Hotel>>> =
            PublishSubject.create<Outcome<List<SearchDomain.Hotel>>>()

    override fun fetchHotels() {
        hotelFetchOutcome.loading(true)
        local.getHotels()
                .performOnBackOutOnMain(scheduler)
                .doAfterNext {
                    if (Synk.shouldSync(SynkKeys.HOTEL_LIST, 2, TimeUnit.HOURS))
                        refreshHotels()
                }
                .subscribe({ hotels ->
                    hotelFetchOutcome.success(hotels)
                }, { error -> handleError(error) })
                .addTo(compositeDisposable)
    }

    override fun refreshHotels() {
        hotelFetchOutcome.loading(true)
        remote.getHotels()
                .performOnBackOutOnMain(scheduler)
                .updateSynkStatus(key = SynkKeys.HOTEL_LIST)
                .subscribe(
                        { it?.let { saveHotels(it) } },
                        { error -> handleError(error) }
                )
                .addTo(compositeDisposable)
    }

    override fun saveHotels(hotels: List<SearchDomain.Hotel?>) {
        if (hotels.isNotEmpty()) {
            local.saveHotels(hotels.filterNotNull())
        } else
            hotelFetchOutcome.failed(HotelException.NoHotels())
    }

    override fun handleError(error: Throwable) {
        hotelFetchOutcome.failed(error)
    }

}
