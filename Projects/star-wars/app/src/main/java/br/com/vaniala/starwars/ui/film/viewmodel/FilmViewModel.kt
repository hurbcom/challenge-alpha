package br.com.vaniala.starwars.ui.film.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import br.com.vaniala.starwars.domain.usecase.GetFilmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
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

    val pagingDataFlow = getFilmsUseCase().cachedIn(viewModelScope)

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
