package com.barreto.android.domain.content.usecase

import com.barreto.android.domain.base.Event
import com.barreto.android.domain.content.IContentRepository
import com.barreto.android.domain.content.model.ContentItem
import io.reactivex.Observable

class AddContentItemUseCase(
    private val repository: IContentRepository
) {

    fun execute(contentItem: ContentItem): Observable<Event<Boolean>> {

        return Observable.concat(Observable.just(Event.loading()), addContentItem(contentItem))
    }

    private fun addContentItem(contentItem: ContentItem): Observable<Event<Boolean>> {

        return repository.addContentItem(contentItem)
            .toSingle { Event.data(true) }
            .onErrorReturn { Event.error(it) }
            .toObservable()
    }
}