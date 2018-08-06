package com.github.felipehjcosta.huchallenge.feature.search.viewmodel

class HotelSectionListItemViewModel(
        private val sectionTitle: String
) : ListItemViewModel {

    override val name: String?
        get() = sectionTitle
}