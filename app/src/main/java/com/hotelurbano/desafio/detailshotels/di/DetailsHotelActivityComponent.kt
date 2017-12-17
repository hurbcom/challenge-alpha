package com.hotelurbano.desafio.detailshotels.di

import com.hotelurbano.desafio.base.di.ActivityScope
import com.hotelurbano.desafio.base.di.component.AppComponent
import com.hotelurbano.desafio.detailshotels.DetailsHotelActivity
import dagger.Component

/**
 * Created by Cristian on 17/12/2017.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(DetailsHotelActivityModule::class))
interface DetailsHotelActivityComponent {

    fun inject(detailsHotelActivity: DetailsHotelActivity)
}