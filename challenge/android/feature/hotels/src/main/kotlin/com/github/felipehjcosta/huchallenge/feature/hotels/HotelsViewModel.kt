package com.github.felipehjcosta.huchallenge.feature.hotels

import com.felipecosta.rxaction.RxAction
import com.felipecosta.rxaction.RxCommand
import io.reactivex.Observable
import io.reactivex.Observable.just
import javax.inject.Inject

class HotelsViewModel @Inject constructor() {

    private val asyncLoadItemsCommand = RxAction<Any, List<String>> {
        just(listOf("Hotel Vilamar Copacabana"))
    }

    val items: Observable<List<String>>
        get() = asyncLoadItemsCommand.elements


    val loadItemsCommand: RxCommand<Any>
        get() = asyncLoadItemsCommand
}