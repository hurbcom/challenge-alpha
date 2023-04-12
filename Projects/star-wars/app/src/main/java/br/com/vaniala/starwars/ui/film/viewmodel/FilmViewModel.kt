package br.com.vaniala.starwars.ui.film.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.vaniala.starwars.core.State
import br.com.vaniala.starwars.domain.model.Films
import br.com.vaniala.starwars.domain.usecase.GetFilmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 12/04/23.
 *
 */
@HiltViewModel
class FilmViewModel @Inject constructor(
    getFilmsUseCase: GetFilmsUseCase,
) : ViewModel() {
    private val _films = MutableStateFlow<State<PagingData<Films>>>(State.Loading)
    val films = _films.asStateFlow()

    val pagingDataFlow = getFilmsUseCase().cachedIn(viewModelScope)

    //    init {
//        getFilms()
//    }

//    private fun getFilms() {
//        viewModelScope.launch {
//            pagingDataFlow = getFilmsUseCase().cachedIn(viewModelScope)
//        }
//    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
