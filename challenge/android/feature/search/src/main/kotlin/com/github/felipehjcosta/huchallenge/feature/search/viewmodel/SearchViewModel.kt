package com.github.felipehjcosta.huchallenge.feature.search.viewmodel

import com.felipecosta.rxaction.RxAction
import com.felipecosta.rxaction.RxCommand
import com.github.felipehjcosta.huchallenge.base.hotels.HotelsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(hotelsRepository: HotelsRepository) {

    private val asyncLoadItemsCommand = RxAction<Any, HotelsListViewModel> {
        hotelsRepository.fetchHotels().map { HotelsListViewModel(it) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    val items: Observable<HotelsListViewModel>
        get() = asyncLoadItemsCommand.elements


    val loadItemsCommand: RxCommand<Any>
        get() = asyncLoadItemsCommand
}