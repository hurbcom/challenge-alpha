package com.wesleyerick.podracer.view.starships

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wesleyerick.podracer.data.model.starships.Starship
import com.wesleyerick.podracer.domain.usecase.starships.StarshipsUseCases
import com.wesleyerick.podracer.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StarshipsViewModel(
    private val useCases: StarshipsUseCases,
) : ViewModel() {

    private val _getList: MutableLiveData<List<Starship>> =
        MutableLiveData(emptyList())
    val list: LiveData<List<Starship>> get() = _getList

    private val _getStarshipsDetails: MutableLiveData<Starship> =
        MutableLiveData(Starship())
    val starshipsDetails: LiveData<Starship> get() = _getStarshipsDetails

    private var _onError: MutableLiveData<String> =
        MutableLiveData(String())
    val onError: LiveData<String> get() = _onError

    fun getList() = viewModelScope.launch(Dispatchers.IO) {
        when (
            val list = useCases.getList()
        ) {
            is Result.Success -> _getList.postValue(list.data.orEmpty())
            is Result.Failure -> onError(list.errorMessage)
        }
    }

    fun getDetails(id: String) = viewModelScope.launch(Dispatchers.IO) {
        when (
            val list = useCases.getDetails(id)
        ) {
            is Result.Success -> _getStarshipsDetails.postValue(list.data ?: Starship())
            is Result.Failure -> onError(list.errorMessage)
        }
    }

    private fun onError(message: String) {
        _onError.postValue(message)
    }
}