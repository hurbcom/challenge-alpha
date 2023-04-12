package br.com.vaniala.starwars.ui.films.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import br.com.vaniala.starwars.domain.usecase.GetFilmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
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

    fun pagingFilter(title: String) =
        pagingDataFlow.map { pagingData ->
            pagingData.filter { film ->
                film.title.lowercase().contains(title.lowercase())
            }
        }
}
