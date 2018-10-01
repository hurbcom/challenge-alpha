package com.belfortdev.hurbchallenge.list.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.belfortdev.hurbchallenge.list.data.ListDataContract
import io.reactivex.disposables.CompositeDisposable

@Suppress("UNCHECKED_CAST")
class ListViewModelFactory(private val repository: ListDataContract.Repository, private val compositeDisposable: CompositeDisposable) :

        ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListViewModel(repository, compositeDisposable) as T
    }

}