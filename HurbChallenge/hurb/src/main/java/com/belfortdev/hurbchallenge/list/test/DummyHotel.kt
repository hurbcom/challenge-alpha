package com.belfortdev.hurbchallenge.list.test

import android.support.annotation.VisibleForTesting
import com.belfortdev.hurbchallenge.core.model.SearchResponse

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
object DummyHotel {

    fun getDummyHotel(id: String) = SearchResponse.Hotel(id, "name$id", SearchResponse.HotelPrice(null, null, null, 145.22, null, null, null, null), SearchResponse.Address("Rio de Janeiro", "Brasil", 43, 55, 33, "RJ", "Av. Lalala", "2222222"), 4f, null, null, "AAAA", false, null, null, null, null, true, true)
    fun getFullResponse(id: String) = SearchResponse.FullResponse(null, null, listOf(getDummyHotel(id)), null)
}