package br.com.hurbandroidchallenge.presentation.screens.character.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hurbandroidchallenge.domain.model.Film
import br.com.hurbandroidchallenge.domain.model.People
import br.com.hurbandroidchallenge.domain.model.Planet
import br.com.hurbandroidchallenge.domain.use_case.GetCharacterByUrlUseCase
import br.com.hurbandroidchallenge.domain.use_case.GetFilmByUrlUseCase
import br.com.hurbandroidchallenge.domain.use_case.GetPlanetByUrlUseCase
import br.com.hurbandroidchallenge.presentation.model.StateUI
import br.com.hurbandroidchallenge.presentation.screens.character.detail.ui.CharacterUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val getCharacterByUrlUseCase: GetCharacterByUrlUseCase,
    private val getFilmByUrlUseCase: GetFilmByUrlUseCase,
    private val getPlanetByUrlUseCase: GetPlanetByUrlUseCase,
    url: String,
) : ViewModel() {

    private val _characterState = MutableStateFlow<StateUI<People>>(StateUI.Idle())
    val characterState = _characterState.asStateFlow()

    private val _filmsState = MutableStateFlow<StateUI<List<Film>>>(StateUI.Idle())
    val filmsState = _filmsState.asStateFlow()

    private val _planetsState = MutableStateFlow<StateUI<Planet>>(StateUI.Idle())
    val planetsState = _planetsState.asStateFlow()

    private val _characterUI = mutableStateOf(CharacterUI())
    val characterUI: State<CharacterUI> = _characterUI

    init {
        loadCharacter(url)
    }

    private fun loadCharacter(url: String) {
        viewModelScope.launch {
            getCharacterByUrlUseCase(url).onStart {
                _characterState.emit(StateUI.Processing())
            }.catch {
                _characterState.emit(StateUI.Error(it.message.orEmpty()))
            }.collect { data ->
                _characterUI.value = characterUI.value.copy(
                    character = data
                )
                _characterState.emit(StateUI.Processed(data))
                loadFilms(data.films)
                loadHomeWorld(data.homeWorld)
            }
        }
    }

    private fun loadFilms(urls: List<String>) {
        viewModelScope.launch {
            urls.forEach { urls ->
                getFilmByUrlUseCase(urls).onStart {
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

    private fun loadHomeWorld(url: String) {
        viewModelScope.launch {
            getPlanetByUrlUseCase(url).onStart {
                _planetsState.emit(StateUI.Processing())
            }.catch {
                _planetsState.emit(StateUI.Error())
            }.collect { data ->
                _characterUI.value = characterUI.value.copy(homeWorld = data)
                _planetsState.emit(StateUI.Processed(data = data))
            }
        }
    }

}