package br.com.vaniala.starwars.ui.characters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import br.com.vaniala.starwars.domain.model.People
import br.com.vaniala.starwars.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 12/04/23.
 *
 */
@HiltViewModel
class CharacterViewModel @Inject constructor(
    getCharactersUseCase: GetCharactersUseCase,
) : ViewModel() {

    private val _filterName = MutableStateFlow("")
    val filterName = _filterName.asStateFlow()

    private val pagingDataFlow: Flow<PagingData<People>> = getCharactersUseCase().cachedIn(viewModelScope)

    fun pagingFilter(title: String): Flow<PagingData<People>> {
        _filterName.value = title
        if (title.isEmpty()) {
            return pagingDataFlow
        }
        return pagingDataFlow.map { pagingData ->
            pagingData.filter { character ->
                character.name?.lowercase()?.contains(title.lowercase()) ?: false
            }
        }.cachedIn(viewModelScope)
    }
}
