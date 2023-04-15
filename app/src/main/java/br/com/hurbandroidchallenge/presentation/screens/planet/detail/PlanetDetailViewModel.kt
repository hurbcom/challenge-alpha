package br.com.hurbandroidchallenge.presentation.screens.planet.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hurbandroidchallenge.data.mapper.characters.toSmallModel
import br.com.hurbandroidchallenge.data.mapper.films.toSmallModel
import br.com.hurbandroidchallenge.domain.model.Planet
import br.com.hurbandroidchallenge.domain.use_case.characters.GetCharacterByUrlUseCase
import br.com.hurbandroidchallenge.domain.use_case.films.GetFilmByUrlUseCase
import br.com.hurbandroidchallenge.domain.use_case.planets.GetPlanetByUrlUseCase
import br.com.hurbandroidchallenge.domain.use_case.planets.SetFavoritePlanetUseCase
import br.com.hurbandroidchallenge.domain.use_case.planets.SetPlanetLastSeenUseCase
import br.com.hurbandroidchallenge.presentation.model.SmallItemModel
import br.com.hurbandroidchallenge.presentation.model.StateUI
import br.com.hurbandroidchallenge.presentation.screens.planet.detail.ui.PlanetUI
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PlanetDetailViewModel(
    private val getPlanetByUrlUseCase: GetPlanetByUrlUseCase,
    private val getCharacterByUrlUseCase: GetCharacterByUrlUseCase,
    private val getFilmByUrlUseCase: GetFilmByUrlUseCase,
    private val setLastSeenUseCase: SetPlanetLastSeenUseCase,
    private val setFavoritePlanetUseCase: SetFavoritePlanetUseCase,
    url: String,
) : ViewModel() {

    private val _planetUI = mutableStateOf(PlanetUI())
    val planetUI: State<PlanetUI> = _planetUI

    private val _planetState = MutableStateFlow<StateUI<Planet>>(StateUI.Idle())
    val planetState = _planetState.asStateFlow()

    private val _filmsState = MutableStateFlow<StateUI<List<SmallItemModel>>>(StateUI.Idle())
    val filmsState = _filmsState.asStateFlow()

    private val _charactersState = MutableStateFlow<StateUI<List<SmallItemModel>>>(StateUI.Idle())
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
                setLastSeenUseCase(data).collect {
                    _planetState.emit(StateUI.Processed(data))
                }
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
            if (_planetUI.value.films.isEmpty())
                _filmsState.emit(StateUI.Error("This planet isn't related with any movie"))
            else
                _filmsState.emit(StateUI.Processed(_planetUI.value.films.map { it.toSmallModel() }))
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
            if (_planetUI.value.characters.isEmpty())
                _charactersState.emit(StateUI.Error("This planet isn't related with any movie"))
            else
                _charactersState.emit(StateUI.Processed(_planetUI.value.characters.map { it.toSmallModel() }))
        }
    }

}