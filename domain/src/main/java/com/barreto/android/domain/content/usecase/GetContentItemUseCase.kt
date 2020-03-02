package com.barreto.android.domain.content.usecase

import com.barreto.android.domain.base.Event
import com.barreto.android.domain.content.IContentRepository
import com.barreto.android.domain.content.model.ContentItem
import io.reactivex.Observable

class GetContentItemUseCase(
    private val repository: IContentRepository
) {

    fun execute(contentItemId: String): Observable<Event<ContentItem>> {

        return Observable.concat(
            Observable.just(Event.loading()),
            getContent(contentItemId)
        )
    }

    private fun getContent(contentItemId: String): Observable<Event<ContentItem>> {

        return repository.getContentItem(contentItemId)
            .map { Event.data(it) }
            .onErrorReturn { Event.error(it) }
            .toObservable()
    }
}