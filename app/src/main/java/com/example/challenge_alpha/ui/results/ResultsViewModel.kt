package com.example.challenge_alpha.ui.results

import android.app.Application
import androidx.lifecycle.*
import com.example.challenge_alpha.api.HurbService
import com.example.challenge_alpha.repository.HurbRepository

class ResultsViewModel(application: Application) : AndroidViewModel(application) {


    private val hurbRepository = HurbRepository(HurbService.create())

    private val queryString = MutableLiveData<String>()
    fun lastQuery(): String? = queryString.value

    val resultDetailLive = hurbRepository.resultDetailLive
    val filterLive = hurbRepository.filterLive
    val headerLive = hurbRepository.headerLive
    val errorLive = hurbRepository.networkErrors

    private val _progressBar = MutableLiveData<Boolean>(true)
    val progressBar: LiveData<Boolean> = _progressBar

    fun search(query: String) {
        queryString.postValue(query)
        hurbRepository.search(query)
    }

    fun requestMore() {
        hurbRepository.requestMore(queryString.value!!)
    }

    fun progressBar(visible: Boolean) {
        _progressBar.value = visible
    }
}