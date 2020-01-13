package com.example.challenge_alpha.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.challenge_alpha.repository.HurbRepository
import com.example.challenge_alpha.repository.LastSeenRepository
import com.example.challenge_alpha.repository.ResultDetailRepository
import javax.inject.Inject

/**
 * ViewModel para tratar os dados necessários do fragmento [HomeFragment].
 * as informações de últimos vistos são buscados do repositório [LastSeenRepository]
 * as informações de últimas buscas são buscados do repositório [ResultDetailRepository]
 * as informações do servidor através do [HurbRepository]
 *
 * As buscas são feitas de acordo com o dado populado em [_queryString] através da função [search]
 * As sugestões são obtidas de acordo com o dado populado em [_suggestionString] através da
 * transformation [getSuggetion] que está sendo observada pelo fragmento [HomeFragment].
 */
class HomeViewModel @Inject constructor(
    lastSeenRepository: LastSeenRepository,
    resultDetailRepository: ResultDetailRepository,
    private val hurbRepository: HurbRepository
) : ViewModel() {


    val getLastSeen = lastSeenRepository.getLastSeen()

    val getLastSearched = resultDetailRepository.getSearch()

    private val _queryString = MutableLiveData<String>()
    val queryString: LiveData<String> = _queryString

    private val _suggestionString = MutableLiveData<String>()
    val getSuggetion  = Transformations.switchMap(_suggestionString) {
        hurbRepository.suggestionSearch(it)
    }


    fun search(query: String) {
        _queryString.postValue(query)
    }

    fun searchSuggestion(suggestion: String) {
        _suggestionString.postValue(suggestion)
    }


}