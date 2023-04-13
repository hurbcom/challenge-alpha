package br.com.hurbandroidchallenge.presentation.screens.character.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hurbandroidchallenge.domain.model.Film
import br.com.hurbandroidchallenge.domain.model.People
import br.com.hurbandroidchallenge.domain.use_case.GetCharacterByUrlUseCase
import br.com.hurbandroidchallenge.domain.use_case.GetFilmByUrlUseCase
import br.com.hurbandroidchallenge.presentation.model.StateUI
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val getCharacterByUrlUseCase: GetCharacterByUrlUseCase,
    private val getFilmByUrlUseCase: GetFilmByUrlUseCase,
) : ViewModel() {

    private val _characterState = MutableStateFlow<StateUI<People>>(StateUI.Idle())
    val characterState = _characterState.asStateFlow()

    private val _filmsState = MutableStateFlow<StateUI<List<Film>>>(StateUI.Idle())
    val filmsState = _filmsState.asStateFlow()

    private val _characterUI = mutableStateOf(CharacterUI())
    val characterUI: State<CharacterUI> = _characterUI

    fun loadCharacter(url: String) {
        viewModelScope.launch {
            getCharacterByUrlUseCase(url, fromRemote = false).onStart {
                _characterState.emit(StateUI.Processing())
            }.catch {
                _characterState.emit(StateUI.Error(it.message.orEmpty()))
            }.collect { data ->
                _characterUI.value = characterUI.value.copy(
                    character = data
                )
                _characterState.emit(StateUI.Processed(data))
                loadFilms(data.films)
            }
        }
    }

    private fun loadFilms(urls: List<String>) {
        viewModelScope.launch {
            urls.forEach { urls ->
                getFilmByUrlUseCase(urls, fromRemote = true).onStart {
                    _filmsState.emit(StateUI.Processing())
                }.catch {
                    _filmsState.emit(StateUI.Error())
                }.collect { data ->
                    _characterUI.value = characterUI.value.copy(
                        films = characterUI.value.films.plus(data)
                    )
                }
            }
            _filmsState.emit(StateUI.Processed(_characterUI.value.films))
        }
    }

}