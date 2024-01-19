package com.br.myapplication.ui.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.myapplication.data.model.Film
import com.br.myapplication.data.remote.SafeResponse
import com.br.myapplication.data.remote.safeRequest
import com.br.myapplication.data.repository.film.IFilmRepository
import kotlinx.coroutines.launch

class FilmsViewModel(val filmRepository: IFilmRepository): ViewModel() {

    private val _filmsList = MutableLiveData<List<Film>>()
    val filmsList: LiveData<List<Film>>
        get() = _filmsList

    fun getFilmList(){

        viewModelScope.launch {
            when(val request = safeRequest {
                filmRepository.getFilmList("1")
            }) {
                is SafeResponse.Success -> {
                    request.value.let {
                        _filmsList.value = it
                    }
                }
                is SafeResponse.GenericError -> {

                }

                is SafeResponse.NetworkError -> {

                }
            }
        }

    }
}