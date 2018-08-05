package com.github.felipehjcosta.huchallenge.feature.search.viewmodel

import com.github.felipehjcosta.huchallenge.base.hotels.Hotel

class HotelsListViewModel(
        private val hotels: List<Hotel>
) {

    val size: Int
        get() = hotels.size

    operator fun get(index: Int): Hotel? = hotels[index]
}