package com.github.felipehjcosta.huchallenge.feature.search.viewmodel

import com.felipecosta.rxaction.RxAction
import com.felipecosta.rxaction.RxCommand
import com.github.felipehjcosta.huchallenge.base.hotels.HotelsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(hotelsRepository: HotelsRepository) {

    private val asyncLoadItemsCommand = RxAction<Any, ListViewModel> {
        hotelsRepository.fetchHotels()
                .map {
                    val hotels = it.filter { it.isHotel }
                    val listItemViewModels = mutableListOf<ListItemViewModel>()
                    hotels.groupBy { it.stars }.toSortedMap(reverseOrder()).forEach {
                        listItemViewModels.add(SectionListItemViewModel(it.key.toString()))
                        it.value.forEach { listItemViewModels.add(HotelListItemViewModel(it)) }
                    }
                    listItemViewModels
                }
                .map { ListViewModel(it) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    val items: Observable<ListViewModel>
        get() = asyncLoadItemsCommand.elements


    val loadItemsCommand: RxCommand<Any>
        get() = asyncLoadItemsCommand
}