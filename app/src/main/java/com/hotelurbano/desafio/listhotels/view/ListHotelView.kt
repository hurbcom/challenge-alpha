package com.hotelurbano.desafio.listhotels.view

import com.hotelurbano.desafio.base.mvp.BaseView
import com.hotelurbano.desafio.listhotels.model.HotelItem

/**
 * Created by Cristian on 17/12/2017.
 */
interface ListHotelView : BaseView {
    fun onResponse(list: List<HotelItem>?)
    fun showProgress()
    fun hideProgress()
    fun noResult()
}