package br.com.hurbandroidchallenge.presentation.screens.film.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hurbandroidchallenge.domain.model.Film
import br.com.hurbandroidchallenge.domain.model.People
import br.com.hurbandroidchallenge.domain.model.Planet
import br.com.hurbandroidchallenge.domain.use_case.characters.GetCharacterByUrlUseCase
import br.com.hurbandroidchallenge.domain.use_case.films.GetFilmByUrlUseCase
import br.com.hurbandroidchallenge.domain.use_case.planets.GetPlanetByUrlUseCase
import br.com.hurbandroidchallenge.presentation.model.StateUI
import br.com.hurbandroidchallenge.presentation.screens.film.detail.ui.FilmDetailUI
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FilmDetailViewModel(
    private val getFilmByUrlUseCase: GetFilmByUrlUseCase,
    private val getCharacterByUrlUseCase: GetCharacterByUrlUseCase,
    private val getPlanetByUrlUseCase: GetPlanetByUrlUseCase,
    url: String,
) : ViewModel() {

    private val _filmDetailUI = mutableStateOf(FilmDetailUI())
    val filmUI: State<FilmDetailUI> = _filmDetailUI

    private val _filmState = MutableStateFlow<StateUI<Film>>(StateUI.Idle())
    val filmState = _filmState.asStateFlow()

    private val _charactersState = MutableStateFlow<StateUI<List<People>>>(StateUI.Idle())
    val charactersState = _charactersState.asStateFlow()

    private val _planetsState = MutableStateFlow<StateUI<List<Planet>>>(StateUI.Idle())
    val planetsState = _planetsState.asStateFlow()

    init {
        loadFilm(url)
    }

    private fun loadFilm(url: String) {
        viewModelScope.launch {
            getFilmByUrlUseCase(url).onStart {
                _filmState.emit(StateUI.Processing())
            }.catch {
                _filmState.emit(StateUI.Error(it.message.orEmpty()))
            }.collect { data ->
                _filmDetailUI.value = filmUI.value.copy(
                    film = data
                )
                _filmState.emit(StateUI.Processed(data))
                loadCharacters(data.characters)
                loadPlanets(data.planets)
            }
        }
    }

    private fun loadCharacters(urls: List<String>) {
        viewModelScope.launch {
            urls.forEach { url ->
                getCharacterByUrlUseCase(url).onStart {
                    _charactersState.emit(StateUI.Processing())
                }.catch {
                    _charactersState.emit(StateUI.Error())
                }.collect { data ->
                    _filmDetailUI.value = filmUI.value.copy(
                        characters = filmUI.value.characters.plus(data)
                    )
                }
            }
            _charactersState.emit(StateUI.Processed(_filmDetailUI.value.characters))
        }
    }

    private fun loadPlanets(urls: List<String>) {
        viewModelScope.launch {
            urls.forEach { url ->
                getPlanetByUrlUseCase(url).onStart {
                    _planetsState.emit(StateUI.Processing())
                }.catch {
                    _planetsState.emit(StateUI.Error(it.message.orEmpty()))
                }.collect { data ->
                    _filmDetailUI.value = filmUI.value.copy(
                        planets = filmUI.value.planets.plus(data)
                    )
                }
                _planetsState.emit(StateUI.Processed(_filmDetailUI.value.planets))
            }
        }
    }
}