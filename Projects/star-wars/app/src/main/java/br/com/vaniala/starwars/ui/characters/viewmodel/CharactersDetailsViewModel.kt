package br.com.vaniala.starwars.ui.characters.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.vaniala.starwars.domain.model.LastSeen
import br.com.vaniala.starwars.domain.model.People
import br.com.vaniala.starwars.domain.usecase.FavoriteCharacterUseCase
import br.com.vaniala.starwars.domain.usecase.SaveLastSeenInBD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *
 */
@HiltViewModel
class CharactersDetailsViewModel @Inject constructor(
    private val saveLastSeenInBD: SaveLastSeenInBD,
    private val favoriteCharacterUseCase: FavoriteCharacterUseCase,
    val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var _isFavorite = MutableStateFlow(savedStateHandle.get<Boolean>("isFavorite") ?: false)
    val isFavorite: StateFlow<Boolean>
        get() = _isFavorite

    private var _lastSeen = MutableStateFlow(false)
    val lastSeen: StateFlow<Boolean>
        get() = _lastSeen

    private val _character: MutableStateFlow<People?> = MutableStateFlow(null)
    val character: StateFlow<People?>
        get() = _character

    fun addLastSeen(lastSeen: LastSeen) = viewModelScope.launch {
        _lastSeen.emit(true)
        saveLastSeenInBD(lastSeen)
    }

    fun favorite(character: People) = viewModelScope.launch {
        character.isFavorite = !character.isFavorite
        _isFavorite.emit(character.isFavorite)
        favoriteCharacterUseCase(character)
    }

    init {
        savedStateHandle.getStateFlow("isFavorite", false).onEach { isFavorite ->
            _isFavorite.value = isFavorite
        }.launchIn(viewModelScope)
    }
}
