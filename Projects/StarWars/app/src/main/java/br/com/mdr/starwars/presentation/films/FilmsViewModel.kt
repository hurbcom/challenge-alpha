package br.com.mdr.starwars.presentation.films

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.mdr.starwars.domain.model.Film
import br.com.mdr.starwars.domain.usecase.FilmUseCase
import br.com.mdr.starwars.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilmsViewModel(
    private val useCase: FilmUseCase
): BaseViewModel() {

    private val _films = MutableStateFlow<PagingData<Film>>(PagingData.empty())
    val films: StateFlow<PagingData<Film>>
        get() = _films

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    override suspend fun refresh() {
        getFilms(_searchQuery.value)
    }

    fun getFilms(query: String) {
        viewModelScope.launch {
            useCase.getFilms(query).cachedIn(viewModelScope).collect{
                _films.emit(it)
            }
        }
    }
}