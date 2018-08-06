package com.github.felipehjcosta.huchallenge.feature.search.viewmodel

import com.github.felipehjcosta.huchallenge.base.hotels.Hotel

class PackageListItemViewModel(
        private val hotel: Hotel
) : ListItemViewModel {
    override val name: String?
        get() = hotel.name

    override val city: String? = null
    override val state: String? = null
    override val price: String? = null
    override val amenity1: String? = null
    override val amenity2: String? = null
    override val amenity3: String? = null
}