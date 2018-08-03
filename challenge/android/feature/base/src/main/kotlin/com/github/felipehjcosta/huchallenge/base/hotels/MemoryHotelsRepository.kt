package com.github.felipehjcosta.huchallenge.base.hotels

import io.reactivex.Observable

internal class MemoryHotelsRepository : HotelsRepository {
    override fun fetchHotels(): Observable<List<String>> {
        return Observable.just(listOf("Hotel Vilamar Copacabana"))
    }
}