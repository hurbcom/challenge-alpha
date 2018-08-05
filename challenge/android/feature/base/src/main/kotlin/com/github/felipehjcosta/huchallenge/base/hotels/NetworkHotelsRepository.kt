package com.github.felipehjcosta.huchallenge.base.hotels

import io.reactivex.Observable

internal class NetworkHotelsRepository(private val searchApi: SearchApi) : HotelsRepository {

    override fun fetchHotels(): Observable<List<Hotel>> {
        return searchApi.searchHotels().map { it.results }
    }
}