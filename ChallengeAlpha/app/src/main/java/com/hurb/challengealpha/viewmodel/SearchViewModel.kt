package com.hurb.challengealpha.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hurb.challengealpha.api.api
import com.hurb.challengealpha.model.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class SearchViewModel : ViewModel() {
    private val search: MutableLiveData<List<Result>> = MutableLiveData()
    private val isLoading = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()
    private var count = 0
    private var page = 1
    private var q = "buzios"

    //Updates when loading content
    fun isLoading(): LiveData<Boolean> {
        return isLoading
    }

    //Returns current value when it isn't null (ex: after configuration changes).
    // Otherwise loads a new search
    fun getSearch(): MutableLiveData<List<Result>> {
        if (search.value == null) {
            loadSearch(q)
        }
        return search
    }

    //Loads first page of search and updates search value and loading
    fun loadSearch(q: String) {
        this.q = q
        page = 1
        disposable.add(
            api.search(q, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    count = response.pagination.count
                    search.value = response.results
                    isLoading.value = false
                }, {
                    count = 0
                    search.value = null
                    isLoading.value = false
                })
        )
        isLoading.value = true
    }

    //Increments page number, updates search value and loading
    fun loadMore() {
        val results: MutableList<Result> = search.value as MutableList<Result>
        page += 1
        if (page <= count) {
            disposable.add(
                api.search(q, page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        count = response.pagination.count
                        results.addAll(response.results)
                        search.value = results
                    }, {
                    })
            )
        }
    }

    //Disposes any observables running when app is finished
    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}