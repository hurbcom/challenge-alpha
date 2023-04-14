package br.com.hurbandroidchallenge.presentation.screens.planet.detail

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
import br.com.hurbandroidchallenge.presentation.screens.planet.detail.ui.PlanetUI
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PlanetDetailViewModel(
    private val getPlanetByUrlUseCase: GetPlanetByUrlUseCase,
    private val getCharacterByUrlUseCase: GetCharacterByUrlUseCase,
    private val getFilmByUrlUseCase: GetFilmByUrlUseCase,
    url: String,
) : ViewModel() {

    private val _planetUI = mutableStateOf(PlanetUI())
    val planetUI: State<PlanetUI> = _planetUI

    private val _planetState = MutableStateFlow<StateUI<Planet>>(StateUI.Idle())
    val planetState = _planetState.asStateFlow()

    private val _filmsState = MutableStateFlow<StateUI<List<Film>>>(StateUI.Idle())
    val filmsState = _filmsState.asStateFlow()

    private val _charactersState = MutableStateFlow<StateUI<List<People>>>(StateUI.Idle())
    val charactersState = _charactersState.asStateFlow()

    init {
        loadPlanet(url)
    }

    private fun loadPlanet(url: String) {
        viewModelScope.launch {
            getPlanetByUrlUseCase(url).onStart {
                _planetState.emit(StateUI.Processing())
            }.catch {
                _planetState.emit(StateUI.Error(it.message.orEmpty()))
            }.collect { data ->
                _planetUI.value = planetUI.value.copy(
                    planet = data
                )
                _planetState.emit(StateUI.Processed(data))
                loadCharacters(data.residents)
                loadFilms(data.films)
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
                    _planetUI.value = planetUI.value.copy(
                        films = planetUI.value.films.plus(data)
                    )
                }
            }
            _filmsState.emit(StateUI.Processed(_planetUI.value.films))
        }
    }

    private fun loadCharacters(urls: List<String>) {
        viewModelScope.launch {
            urls.forEach { urls ->
                getCharacterByUrlUseCase(urls).onStart {
                    _charactersState.emit(StateUI.Processing())
                }.catch {
                    _charactersState.emit(StateUI.Error())
                }.collect { data ->
                    _planetUI.value = planetUI.value.copy(
                        characters = planetUI.value.characters.plus(data)
                    )
                }
            }
            _charactersState.emit(StateUI.Processed(_planetUI.value.characters))
        }
    }

}