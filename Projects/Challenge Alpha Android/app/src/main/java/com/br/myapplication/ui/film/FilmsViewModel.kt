package com.br.myapplication.ui.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.br.myapplication.data.model.Film
import com.br.myapplication.data.remote.FilmsPagingSource
import com.br.myapplication.data.remote.SafeResponse
import com.br.myapplication.data.remote.safeRequest
import com.br.myapplication.data.repository.film.IFilmRepository
import kotlinx.coroutines.launch

class FilmsViewModel(val filmRepository: IFilmRepository): ViewModel() {

    private val _filmsList = Pager(
        config = PagingConfig(pageSize = 10),
        initialKey = 1
    ) {
        FilmsPagingSource(filmRepository)
    }.flow.cachedIn(viewModelScope).asLiveData()

    val filmsList: LiveData<PagingData<Film>>
        get() = _filmsList


}