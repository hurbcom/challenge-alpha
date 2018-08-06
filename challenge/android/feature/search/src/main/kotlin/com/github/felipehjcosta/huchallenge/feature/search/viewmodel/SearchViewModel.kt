package com.github.felipehjcosta.huchallenge.feature.search.viewmodel

import com.felipecosta.rxaction.RxAction
import com.felipecosta.rxaction.RxCommand
import com.github.felipehjcosta.huchallenge.base.hotels.Hotel
import com.github.felipehjcosta.huchallenge.base.hotels.HotelsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(hotelsRepository: HotelsRepository) {

    private val asyncLoadItemsCommand = RxAction<Any, ListViewModel> {
        hotelsRepository.fetchHotels()
                .map(::mapSearchedHotelsToListViewModel)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun mapSearchedHotelsToListViewModel(list: List<Hotel>): ListViewModel {
        val hotels = list.filter { it.isHotel }
        val packages = list.filter { it.isPackage }
        val listItemViewModels = mutableListOf<ListItemViewModel>()
        hotels.groupBy { it.stars }.toSortedMap(reverseOrder()).forEach {
            listItemViewModels.add(HotelSectionListItemViewModel(it.key.toString()))
            it.value.forEach { listItemViewModels.add(HotelListItemViewModel(it)) }
        }
        if (packages.isNotEmpty()) {
            listItemViewModels.add(PackageSectionListItemViewModel())
            packages.forEach { listItemViewModels.add(PackageListItemViewModel(it)) }
        }
        return ListViewModel(listItemViewModels)
    }

    val loadItemsCommand: RxCommand<Any>
        get() = asyncLoadItemsCommand

    val items: Observable<ListViewModel>
        get() = asyncLoadItemsCommand.elements

    val showLoading: Observable<Boolean>
        get() = asyncLoadItemsCommand.executing
}