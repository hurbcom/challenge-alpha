package com.barreto.android.domain.content.usecase

import com.barreto.android.domain.base.Event
import com.barreto.android.domain.content.IContentRepository
import com.barreto.android.domain.content.model.ContentItem
import io.reactivex.Observable

class DeleteContentItemUseCase(
    private val repository: IContentRepository
) {

    fun execute(contentItem: ContentItem): Observable<Event<Boolean>> {

        return Observable.concat(Observable.just(Event.loading()), deleteContentItem(contentItem))
    }

    private fun deleteContentItem(contentItem: ContentItem): Observable<Event<Boolean>> {

        return repository.deleteContentItem(contentItem)
            .toSingle { Event.data(true) }
            .onErrorReturn { Event.error(it) }
            .toObservable()
    }
}