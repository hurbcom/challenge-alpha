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
import com.br.myapplication.data.dao.FilmsDao
import com.br.myapplication.data.model.Film
import com.br.myapplication.data.remote.FilmsPagingSource
import com.br.myapplication.data.repository.film.IFilmRepository
import kotlinx.coroutines.launch

class FilmsViewModel(
    val filmRepository: IFilmRepository,
     val filmsDao: FilmsDao
): ViewModel() {

    private val _filmsList = Pager(
        config = PagingConfig(pageSize = 10),
        initialKey = 1
    ) {
        FilmsPagingSource(filmRepository, filmsDao)
    }.flow.cachedIn(viewModelScope).asLiveData()

    val filmsList: LiveData<PagingData<Film>>
        get() = _filmsList

//    fun fetchData() : MutableLiveData<PagingData<Film>> {
//
//        val pager = Pager(
//            config = PagingConfig(pageSize = 6),
//            initialKey = 1
//        ) {
//            FilmsPagingSource(filmRepository)
//        }.flow.cachedIn(viewModelScope).asLiveData()
//
//        filmRepository.saveFilm(filmsList.value)
//        return pager.
//        viewModelScope.launch {
//            try {
//
//                val response  = filmRepository.getFilmsPaging().
//                cachedIn(viewModelScope).asLiveData()
//                     _filmsList.value = response.value
//            } catch (e: Exception) {
//                // Handle exceptions if needed
//            }
//        }
//    }

}