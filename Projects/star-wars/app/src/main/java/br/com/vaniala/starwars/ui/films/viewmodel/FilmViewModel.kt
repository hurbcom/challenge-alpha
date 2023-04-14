package br.com.vaniala.starwars.ui.films.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.vaniala.starwars.domain.model.Film
import br.com.vaniala.starwars.domain.usecase.GetFilmsBDAndRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 12/04/23.
 *
 */

@ExperimentalPagingApi
@HiltViewModel
class FilmViewModel @Inject constructor(
    private val getFilmsBDAndRemoteUseCase: GetFilmsBDAndRemoteUseCase,
) : ViewModel() {

    private val _filterTitle = MutableStateFlow("")
    val filterTitle: StateFlow<String>
        get() = _filterTitle

    private val _films = MutableSharedFlow<PagingData<Film>>()
    val films: SharedFlow<PagingData<Film>>
        get() = _films

    fun pagingFilter(title: String) {
        _filterTitle.value = title
        viewModelScope.launch {
            getFilmsBDAndRemoteUseCase(_filterTitle.value).cachedIn(this).collect {
                _films.emit(it)
            }
        }
    }
}
