package br.com.mdr.starwars.ui.presentation.characters.detail

import androidx.lifecycle.SavedStateHandle
import br.com.mdr.starwars.common.Constants.CHARACTER_NAME_KEY
import br.com.mdr.starwars.domain.model.Character
import br.com.mdr.starwars.domain.usecase.CharacterDetailUseCase
import br.com.mdr.starwars.ui.presentation.base.BaseDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharacterDetailViewModel(
    private val useCase: CharacterDetailUseCase,
    private val savedStateHandle: SavedStateHandle
): BaseDetailViewModel() {
    private val _character: MutableStateFlow<Character?> = MutableStateFlow(null)
    val character: StateFlow<Character?> = _character

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    init {
        getCharacter()
    }

    override suspend fun refresh() {
        getCharacter()
    }

    private fun getCharacter() {
        val name = _character.value?.name ?: savedStateHandle.get<String>(CHARACTER_NAME_KEY)

        name?.let {
            launch(Dispatchers.IO) {
                val character = useCase.getSelectedCharacter(it)
                _character.emit(character)
                _isFavorite.emit(character.isFavorite)
                useCase.saveLastSeen(character)
            }
        }
    }

    override fun setFavorite() {
        _character.value?.let {
            it.isFavorite = !it.isFavorite

            launch(Dispatchers.IO) {
                _isFavorite.emit(it.isFavorite)
                _character.emit(it)
                useCase.setFavorite(it.isFavorite, it.name)
            }
        }
    }
}