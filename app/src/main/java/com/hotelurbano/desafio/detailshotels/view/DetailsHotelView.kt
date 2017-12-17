package com.hotelurbano.desafio.detailshotels.view

import com.hotelurbano.desafio.base.mvp.BaseView
import com.hotelurbano.desafio.listhotels.model.HotelItem

/**
 * Created by Cristian on 17/12/2017.
 */
interface DetailsHotelView : BaseView {
    fun setDetails(item: HotelItem)
}