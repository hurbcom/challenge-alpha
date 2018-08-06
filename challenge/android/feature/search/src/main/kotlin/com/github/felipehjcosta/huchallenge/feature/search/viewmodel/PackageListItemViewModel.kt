package com.github.felipehjcosta.huchallenge.feature.search.viewmodel

import com.github.felipehjcosta.huchallenge.base.hotels.Hotel

class PackageListItemViewModel(
        private val hotel: Hotel
) : ListItemViewModel {
    override val name: String?
        get() = hotel.name
}