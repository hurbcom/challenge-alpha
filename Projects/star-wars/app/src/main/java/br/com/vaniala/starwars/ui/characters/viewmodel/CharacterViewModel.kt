package br.com.vaniala.starwars.ui.characters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.vaniala.starwars.domain.model.People
import br.com.vaniala.starwars.domain.usecase.GetCharactersBDAndRemoteUseCase
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
@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharactersBDUseCase: GetCharactersBDAndRemoteUseCase,
) : ViewModel() {

    private val _filterName = MutableStateFlow("")
    val filterName: StateFlow<String>
        get() = _filterName

    private val _characters = MutableSharedFlow<PagingData<People>>()
    val characters: SharedFlow<PagingData<People>>
        get() = _characters

    fun pagingFilter(name: String) {
        _filterName.value = name
        viewModelScope.launch {
            getCharactersBDUseCase(_filterName.value).cachedIn(viewModelScope).collect {
                _characters.emit(it)
            }
        }
    }
}
