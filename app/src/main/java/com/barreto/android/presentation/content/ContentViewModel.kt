package com.barreto.android.presentation.content

import androidx.lifecycle.MutableLiveData
import com.barreto.android.domain.base.Event
import com.barreto.android.domain.content.model.ContentItem
import com.barreto.android.domain.content.usecase.*
import com.barreto.android.domain.util.ISchedulerProvider
import com.barreto.android.presentation.base.BaseViewModel
import io.reactivex.rxkotlin.addTo

class ContentViewModel(
    scheduler: ISchedulerProvider,
    private val getContentListUseCase: GetContentListUseCase,
    private val addContentItemUseCase: AddContentItemUseCase,
    private val deleteContentItemUseCase: DeleteContentItemUseCase,
    private val getFavoriteContentListUseCase: GetFavoriteContentListUseCase,
    private val getContentItemUseCase: GetContentItemUseCase
    ) : BaseViewModel(scheduler) {

    private var currentPage: Int = 1
    private var totalItemCount: Int = 0
    private var currentItemCount: Int = 0
    private var queryText = ""

    val hasNextPage = MutableLiveData<Boolean>().apply { value = true }

    val error = MutableLiveData<Event.Error?>().apply { value = null }

    val total = MutableLiveData<Int>().apply { value = 0 }

    val contentList = MutableLiveData<Event<List<ContentItem>>>().apply { value = Event.idle() }

    val favoriteList = MutableLiveData<Event<List<ContentItem>>>().apply { value = Event.idle() }

    val favorite = MutableLiveData<Event<Boolean>>().apply { value = Event.idle() }

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

    fun getFavoriteList() {

        getFavoriteContentListUseCase.execute()
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe {
                when (it) {
                    is Event.Data -> favoriteList.postValue(Event.data(it.data))
                    is Event.Loading -> favoriteList.postValue(it)
                    is Event.Error -> favoriteList.postValue(it)
                }
            }
            .addTo(disposables)
    }

    fun addFavoriteList(contentItem: ContentItem) {

        addContentItemUseCase.execute(contentItem)
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe {
                when (it) {
                    is Event.Data -> favorite.postValue(it)
                    is Event.Loading -> favorite.postValue(it)
                    is Event.Error -> favorite.postValue(it)
                }
            }
            .addTo(disposables)
    }

    fun deleteFavoriteList(contentItem: ContentItem) {

        deleteContentItemUseCase.execute(contentItem)
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe {
                when (it) {
                    is Event.Data -> favorite.postValue(it)
                    is Event.Loading -> favorite.postValue(it)
                    is Event.Error -> favorite.postValue(it)
                }
            }
            .addTo(disposables)
    }

    fun getContentItem(contentItemId: String) {

        getContentItemUseCase.execute(contentItemId)
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe {
                when (it) {
                    is Event.Data -> content.postValue(Event.data(it.data))
                    is Event.Loading -> content.postValue(it)
                    is Event.Error -> content.postValue(it)
                }
            }
            .addTo(disposables)
    }
}