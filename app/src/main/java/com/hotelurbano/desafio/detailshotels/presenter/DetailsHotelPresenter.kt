package com.hotelurbano.desafio.detailshotels.presenter

import com.hotelurbano.desafio.base.mvp.BasePresenter
import com.hotelurbano.desafio.base.util.SchedulerProvider
import com.hotelurbano.desafio.detailshotels.view.DetailsHotelView
import com.hotelurbano.desafio.listhotels.model.HotelItem
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Cristian on 17/12/2017.
 */
class DetailsHotelPresenter @Inject constructor(disposable: CompositeDisposable, scheduler: SchedulerProvider) : BasePresenter<DetailsHotelView>(disposable, scheduler) {

    fun setDetails(item: HotelItem) {
        view?.setDetails(item)
    }
}