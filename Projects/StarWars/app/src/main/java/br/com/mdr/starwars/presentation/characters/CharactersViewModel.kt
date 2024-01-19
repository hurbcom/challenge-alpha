package br.com.mdr.starwars.presentation.characters

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.mdr.starwars.domain.model.Character
import br.com.mdr.starwars.domain.usecase.CharacterUseCase
import br.com.mdr.starwars.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharactersViewModel(
    private val useCase: CharacterUseCase
): BaseViewModel() {

    private val _characters = MutableStateFlow<PagingData<Character>>(PagingData.empty())
    val characters: StateFlow<PagingData<Character>>
        get() = _characters

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    override suspend fun refresh() {
        getCharacters(_searchQuery.value)
    }

    fun getCharacters(query: String) {
        launch {
            useCase.getCharacters(query).cachedIn(viewModelScope).collect{
                _characters.emit(it)
            }
        }
    }
}