package com.barreto.android.domain.content.usecase

import com.barreto.android.domain.base.Event
import com.barreto.android.domain.content.IContentRepository
import com.barreto.android.domain.content.model.ContentItem
import io.reactivex.Observable

class GetContentItemListUseCase(
    private val repository: IContentRepository
) {

    fun execute(): Observable<Event<List<ContentItem>>> {

        return Observable.concat(
            Observable.just(Event.loading()),
            getContentItemList()
        )
    }

    private fun getContentItemList(): Observable<Event<List<ContentItem>>> {

        return repository.getFavoriteContentList()
            .map { Event.data(it) }
            .onErrorReturn { Event.error(it) }
            .toObservable()
    }
}