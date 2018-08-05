package com.github.felipehjcosta.huchallenge.base.hotels

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("api")
    fun searchHotels(@Query("q") query: String = "Rio de Janeiro"): Observable<SearchApiResponseWrapper>
}