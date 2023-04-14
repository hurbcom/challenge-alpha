package br.com.hurbandroidchallenge.presentation.screens.character.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hurbandroidchallenge.data.remote.config.ApiUrls
import br.com.hurbandroidchallenge.domain.model.People
import br.com.hurbandroidchallenge.domain.model.base.PagedList
import br.com.hurbandroidchallenge.domain.use_case.GetCharactersUseCase
import br.com.hurbandroidchallenge.presentation.model.StateUI
import br.com.hurbandroidchallenge.presentation.screens.character.list.ui.CharactersUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _charactersUI = mutableStateOf(CharactersUI())
    val characters: State<CharactersUI> = _charactersUI

    private val _charactersState = MutableStateFlow<StateUI<PagedList<People>>>(StateUI.Idle())
    val charactersState = _charactersState.asStateFlow()

    private val _loadMoreState = MutableStateFlow<StateUI<PagedList<People>>>(StateUI.Idle())
    val loadMoreState = _loadMoreState.asStateFlow()

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        viewModelScope.launch {
            getCharactersUseCase(url = ApiUrls.characters, clearLocalDataSource = false).onStart {
                _charactersState.emit(StateUI.Processing())
            }.catch {
                _charactersState.emit(StateUI.Error(it.message.orEmpty()))
            }.collect { data ->
                _charactersUI.value = characters.value.copy(
                    characters = data.results,
                    nextPage = data.next
                )
                _charactersState.emit(StateUI.Processed(data))
            }
        }
    }

    fun loadMoreCharacters() {
        viewModelScope.launch {
            _charactersUI.value.nextPage?.let { nextPage ->
                getCharactersUseCase(url = nextPage, clearLocalDataSource = false).onStart {
                    _loadMoreState.emit(StateUI.Processing())
                }.catch {
                    _loadMoreState.emit(StateUI.Error(it.message.orEmpty()))
                }.collect { data ->
                    _charactersUI.value = characters.value.copy(
                        characters = data.results,
                        nextPage = data.next
                    )
                    _loadMoreState.emit(StateUI.Processed(data))
                }
            }
        }
    }

}