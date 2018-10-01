package com.belfortdev.hurbchallenge.list.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.belfortdev.hurbchallenge.core.extension.toLiveData
import com.belfortdev.hurbchallenge.core.model.SearchDomain
import com.belfortdev.hurbchallenge.core.network.Outcome
import com.belfortdev.hurbchallenge.list.data.ListDataContract
import com.belfortdev.hurbchallenge.list.injection.HotelDH
import io.reactivex.disposables.CompositeDisposable

class ListViewModel(private val repo: ListDataContract.Repository,
                    private val compositeDisposable: CompositeDisposable) : ViewModel() {

    val hotelsOutcome: LiveData<Outcome<List<SearchDomain.Hotel>>> by lazy {
        repo.hotelFetchOutcome.toLiveData(compositeDisposable)
    }

    fun getHotels() {
        if (hotelsOutcome.value == null) {
            repo.fetchHotels()
        }
    }

    fun refreshPosts() {
        repo.refreshHotels()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        HotelDH.destroyListComponent()
    }
}