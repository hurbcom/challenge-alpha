package com.example.challenge_alpha.ui.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challenge_alpha.repository.HurbRepository
import com.example.challenge_alpha.ui.home.HomeFragment
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

/**
 * ViewModel para tratar os dados necessários do fragmento [ResultsFragment].
 * As informações do servidor são obtidas através do [HurbRepository] com a query sendo [queryString]
 * que foi passada do [HomeFragment]
 *
 */
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