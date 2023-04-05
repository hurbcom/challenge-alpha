package com.example.starwars.presentation.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwars.presentation.model.Item

@Suppress("PropertyName")
abstract class ViewModelItem:ViewModel() {
   protected open val _nextPageLiveData = MutableLiveData("1")
   open val nextPageLiveData: LiveData<String> = _nextPageLiveData
    protected open val _previousPageLiveData = MutableLiveData("1")
   open val previousPageLiveData: LiveData<String> = _previousPageLiveData

    protected open val _actualItemListLiveData = MutableLiveData<List<Item>>()
    open val actualItemListLiveData: LiveData<List<Item>> = _actualItemListLiveData

    protected open val _loadingLiveData = MutableLiveData<Boolean>()
    open val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    protected open val _requestError = MutableLiveData<String>()
    open val requestError: LiveData<String> = _requestError
    open fun getItems(page: String){}
}