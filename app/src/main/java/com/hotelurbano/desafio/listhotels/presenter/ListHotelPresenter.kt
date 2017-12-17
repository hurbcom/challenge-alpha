package com.hotelurbano.desafio.listhotels.presenter

import com.hotelurbano.desafio.api.Endpoints
import com.hotelurbano.desafio.base.util.SchedulerProvider
import com.hotelurbano.desafio.base.mvp.BasePresenter
import com.hotelurbano.desafio.listhotels.view.ListHotelView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Cristian on 17/12/2017.
 */
class ListHotelPresenter @Inject constructor(var api: Endpoints, disposable: CompositeDisposable, scheduler: SchedulerProvider) : BasePresenter<ListHotelView>(disposable, scheduler) {

    fun getHotels(city: String) {
        view?.showProgress()
        disposable.add(api.getHotels(city)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe(
                { result ->
                    view?.hideProgress()
                    view?.onResponse(result?.results)

                    if (result.results == null || result.results.isEmpty()) {
                        view?.noResult()
                    }
                },
                { _ ->
                    view?.hideProgress()
                    view?.onError()
                })
        )
    }
}