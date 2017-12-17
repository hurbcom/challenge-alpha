package com.hotelurbano.desafio.listhotels.di

import com.hotelurbano.desafio.api.Endpoints
import com.hotelurbano.desafio.base.di.ActivityScope
import com.hotelurbano.desafio.base.util.AppSchedulerProvider
import com.hotelurbano.desafio.listhotels.presenter.ListHotelPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Cristian on 17/12/2017.
 */
@Module
class ListHotelActivityModule {

    @Provides
    @ActivityScope
    internal fun providesListTravelPresenter(api: Endpoints, disposable: CompositeDisposable, scheduler: AppSchedulerProvider): ListHotelPresenter =
        ListHotelPresenter(api, disposable, scheduler)
}