package com.hotelurbano.desafio.listhotels.filter

import com.hotelurbano.desafio.listhotels.model.HotelItem

/**
 * Created by Cristian on 17/12/2017.
 */
interface Criteria {
    fun startsCriteria(items: List<HotelItem>?): List<HotelItem>
}