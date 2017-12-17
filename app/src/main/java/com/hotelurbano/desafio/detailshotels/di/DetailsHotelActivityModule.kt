package com.hotelurbano.desafio.detailshotels.di

import com.hotelurbano.desafio.base.di.ActivityScope
import com.hotelurbano.desafio.base.util.AppSchedulerProvider
import com.hotelurbano.desafio.detailshotels.presenter.DetailsHotelPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Cristian on 17/12/2017.
 */
@Module
class DetailsHotelActivityModule {

    @Provides
    @ActivityScope
    internal fun providesDetailsHotelPresenter(disposable: CompositeDisposable, scheduler: AppSchedulerProvider): DetailsHotelPresenter =
        DetailsHotelPresenter(disposable, scheduler)
}