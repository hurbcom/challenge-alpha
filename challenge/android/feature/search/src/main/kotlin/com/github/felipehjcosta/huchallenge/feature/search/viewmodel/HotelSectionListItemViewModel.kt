package com.github.felipehjcosta.huchallenge.feature.search.viewmodel

class HotelSectionListItemViewModel(
        private val sectionTitle: String
) : ListItemViewModel {

    override val name: String?
        get() = sectionTitle

    override val city: String? = null
    override val state: String? = null
    override val price: String? = null
    override val amenity1: String? = null
    override val amenity2: String? = null
    override val amenity3: String? = null
    override val imageUrl: String? = null
}