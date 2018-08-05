package com.github.felipehjcosta.huchallenge.base.hotels

import io.reactivex.Observable

class NetworkHotelsRepository(private val searchApi: SearchApi) : HotelsRepository {

    override fun fetchHotels(): Observable<List<String>> {
        return searchApi.searchHotels().map { it.results.map { it.name } }
    }
}