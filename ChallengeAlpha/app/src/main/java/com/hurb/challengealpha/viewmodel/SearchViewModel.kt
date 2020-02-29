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
    private var page: Int = 1

    fun isLoading(): LiveData<Boolean>? {
        return isLoading
    }

    fun getSearch(q: String): MutableLiveData<List<Result>> {
        if (search.value == null) {
            loadSearch(q)
        }
        return search
    }

    private fun loadSearch(q: String) {
        disposable.add(
            api.search(q, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    search.postValue(response.results)
                    isLoading.postValue(false)
                }, {
                    search.postValue(null)
                    isLoading.postValue(false)
                })
        )
        isLoading.postValue(true)
    }

    fun loadMore(q: String) {
        val results: MutableList<Result> = search.value as MutableList<Result>
        page += 1
        disposable.add(
            api.search(q, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    results.addAll(response.results)
                    search.postValue(results)
                }, {
                    search.postValue(results)
                    page -= 1
                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}