package com.hotelurbano.desafio.listhotels.di

import com.hotelurbano.desafio.base.di.ActivityScope
import com.hotelurbano.desafio.base.di.component.AppComponent
import com.hotelurbano.desafio.listhotels.ListHotelActivity
import dagger.Component

/**
 * Created by Cristian on 17/12/2017.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ListHotelActivityModule::class))
interface ListHotelActivityComponent {

    fun inject(listHotelActivity: ListHotelActivity)
}
