package com.example.challenge_alpha.ui.results

import androidx.lifecycle.*
import com.example.challenge_alpha.repository.HurbRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class ResultsViewModel @AssistedInject constructor(
    hurbRepository: HurbRepository,
    @Assisted queryString: String
) : ViewModel() {


    @AssistedInject.Factory
    interface Factory {
        fun create(queryString: String): ResultsViewModel
    }

    private val _progressBar = MutableLiveData<Boolean>(true)
    val progressBar: LiveData<Boolean> = _progressBar


    val resultsDetailLive = hurbRepository.searchResult


    init {

        hurbRepository.search(queryString)

    }


    fun progressBar(visible: Boolean) {
        _progressBar.value = visible
    }
}