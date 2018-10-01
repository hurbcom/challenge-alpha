package com.belfortdev.hurbchallenge.core.data.remote

import com.belfortdev.hurbchallenge.core.model.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("/api?q=Rio%20de%20Janeiro")
    fun getAllHotelsFromRio(): Single<SearchResponse.FullResponse>

    @GET("/api")
    fun getHotels(@Query("q", encoded = true) searchQuery: String): Single<List<SearchResponse.Hotel>>

}