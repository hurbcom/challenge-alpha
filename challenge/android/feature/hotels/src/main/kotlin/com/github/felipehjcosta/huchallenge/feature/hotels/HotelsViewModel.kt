package com.github.felipehjcosta.huchallenge.feature.hotels

import com.felipecosta.rxaction.RxAction
import com.felipecosta.rxaction.RxCommand
import com.github.felipehjcosta.huchallenge.base.hotels.HotelsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HotelsViewModel @Inject constructor(hotelsRepository: HotelsRepository) {

    private val asyncLoadItemsCommand = RxAction<Any, List<String>> {
        hotelsRepository.fetchHotels()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    val items: Observable<List<String>>
        get() = asyncLoadItemsCommand.elements


    val loadItemsCommand: RxCommand<Any>
        get() = asyncLoadItemsCommand
}