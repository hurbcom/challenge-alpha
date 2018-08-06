package com.github.felipehjcosta.huchallenge.base.hotels

import io.reactivex.Observable

interface HotelsRepository {
    fun fetchHotels(): Observable<List<Hotel>>
}