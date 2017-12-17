package com.hotelurbano.desafio.api

import com.hotelurbano.desafio.listhotels.model.HotelResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Cristian on 17/12/2017.
 */
interface Endpoints {
    @GET("/busca/api")
    fun search(@Query("q") city: String): Observable<HotelResponse>
}