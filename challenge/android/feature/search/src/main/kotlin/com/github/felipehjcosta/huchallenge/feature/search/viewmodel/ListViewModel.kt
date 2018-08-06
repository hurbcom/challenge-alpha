package com.github.felipehjcosta.huchallenge.feature.search.viewmodel

class ListViewModel(private val items: List<ListItemViewModel>) {

    val size: Int = items.size

    operator fun get(index: Int): ListItemViewModel? = items[index]

    fun isSection(index: Int): Boolean = items[index] is HotelSectionListItemViewModel

    fun isPackageSection(index: Int): Boolean = items[index] is PackageSectionListItemViewModel

    fun isPackage(index: Int): Boolean = items[index] is PackageListItemViewModel
}