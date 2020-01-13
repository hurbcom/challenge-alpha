package com.example.challenge_alpha.ui.home


import androidx.lifecycle.*
import com.example.challenge_alpha.model.Suggestions
import com.example.challenge_alpha.repository.HurbRepository
import com.example.challenge_alpha.repository.LastSeenRepository
import com.example.challenge_alpha.repository.ResultDetailRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    lastSeenRepository: LastSeenRepository,
    resultDetailRepository: ResultDetailRepository,
    private val hurbRepository: HurbRepository
) : ViewModel() {


    val getLastSeen = lastSeenRepository.getLastSeen()

    val getLastSearched = resultDetailRepository.getSearch()

    private val _queryString = MutableLiveData<String>()
    private val _suggestionString = MutableLiveData<String>()
    val getSuggetion  = Transformations.switchMap(_suggestionString) { hurbRepository.suggestionSearch(it)}

    fun search(query: String) {
        _queryString.postValue(query)
    }

    fun searchSuggestion(suggestion: String) {
        _suggestionString.postValue(suggestion)
    }


}