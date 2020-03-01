package com.barreto.android.presentation.content

import androidx.lifecycle.MutableLiveData
import com.barreto.android.domain.base.Event
import com.barreto.android.domain.content.model.ContentItem
import com.barreto.android.domain.content.usecase.GetContentListUseCase
import com.barreto.android.domain.util.ISchedulerProvider
import com.barreto.android.presentation.base.BaseViewModel
import io.reactivex.rxkotlin.addTo

class ContentViewModel(
    scheduler: ISchedulerProvider,
    private val getContentListUseCase: GetContentListUseCase
) : BaseViewModel(scheduler) {

    private var currentPage: Int = 1
    private var totalItemCount: Int = 0
    private var currentItemCount: Int = 0
    private var queryText = ""

    val hasNextPage = MutableLiveData<Boolean>().apply { value = true }

    val error = MutableLiveData<Event.Error?>().apply { value = null }

    val total = MutableLiveData<Int>().apply { value = 0 }

    val contentList = MutableLiveData<Event<List<ContentItem>>>().apply { value = Event.idle() }

    val content = MutableLiveData<Event<ContentItem>>().apply { value = Event.idle() }

    private fun queryParams(): HashMap<String, Any> {

        return HashMap<String, Any>().apply {
            queryText.takeIf { it.isNotBlank() }?.let { this["q"] = it }
        }
    }

    fun getContentList(queryText: String = "") {

        currentPage = 1
        this.queryText = queryText

        getContentListUseCase.execute(queryParams())
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe {
                when (it) {
                    is Event.Data -> {
                        currentPage += 1
                        totalItemCount = it.data.total
                        total.postValue(totalItemCount)
                        currentItemCount = it.data.items?.size ?: 0
                        hasNextPage.value = (currentItemCount < totalItemCount)
                        contentList.postValue(Event.data(it.data.items ?: emptyList()))
                    }
                    is Event.Loading -> contentList.postValue(it)
                    is Event.Error -> contentList.postValue(it)
                }
            }
            .addTo(disposables)
    }

    fun loadMore() {
        if (currentItemCount < totalItemCount) {

            getContentListUseCase.execute(queryParams().apply {
                this["page"] = currentPage
            })
                .subscribeOn(scheduler.backgroundThread())
                .observeOn(scheduler.mainThread())
                .subscribe {
                    when (it) {
                        is Event.Data -> {
                            currentPage += 1
                            totalItemCount = it.data.total
                            total.postValue(totalItemCount)
                            currentItemCount += it.data.items?.size ?: 0
                            hasNextPage.value = (currentItemCount < totalItemCount)

                            val cList = (contentList.value as? Event.Data)?.data ?: emptyList()
                            val nList = it.data.items ?: emptyList()

                            contentList.postValue(Event.data(cList + nList))
                        }
                        is Event.Error -> error.postValue(it)
                    }
                }
                .addTo(disposables)
        } else {
            hasNextPage.postValue(false)
        }
    }

}