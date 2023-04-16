package br.com.hurbandroidchallenge.presentation.screens.film.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hurbandroidchallenge.data.mapper.characters.toSmallModel
import br.com.hurbandroidchallenge.data.mapper.planets.toSmallModel
import br.com.hurbandroidchallenge.domain.model.Film
import br.com.hurbandroidchallenge.domain.use_case.characters.GetCharacterByUrlUseCase
import br.com.hurbandroidchallenge.domain.use_case.films.GetFilmByUrlUseCase
import br.com.hurbandroidchallenge.domain.use_case.films.SetFavoriteFilmUseCase
import br.com.hurbandroidchallenge.domain.use_case.films.SetFilmLastSeenUseCase
import br.com.hurbandroidchallenge.domain.use_case.planets.GetPlanetByUrlUseCase
import br.com.hurbandroidchallenge.presentation.model.SmallItemModel
import br.com.hurbandroidchallenge.presentation.model.StateUI
import br.com.hurbandroidchallenge.presentation.screens.film.detail.ui.FilmDetailUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FilmDetailViewModel(
    private val getFilmByUrlUseCase: GetFilmByUrlUseCase,
    private val getCharacterByUrlUseCase: GetCharacterByUrlUseCase,
    private val getPlanetByUrlUseCase: GetPlanetByUrlUseCase,
    private val setFilmLastSeenUseCase: SetFilmLastSeenUseCase,
    private val setFavoriteFilmsUseCase: SetFavoriteFilmUseCase,
    url: String,
) : ViewModel() {

    private val _filmUI = mutableStateOf(FilmDetailUI())
    val filmUI: State<FilmDetailUI> = _filmUI

    private val _filmState = MutableStateFlow<StateUI<Film>>(StateUI.Idle())
    val filmState = _filmState.asStateFlow()

    private val _charactersState = MutableStateFlow<StateUI<List<SmallItemModel>>>(StateUI.Idle())
    val charactersState = _charactersState.asStateFlow()

    private val _planetsState = MutableStateFlow<StateUI<List<SmallItemModel>>>(StateUI.Idle())
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
                _filmUI.value = filmUI.value.copy(
                    film = data
                )
                setFilmLastSeenUseCase(data).collect {
                    _filmState.emit(StateUI.Processed(data))
                }
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
                    _filmUI.value = filmUI.value.copy(
                        characters = filmUI.value.characters.plus(data)
                    )
                }
                if (_filmUI.value.characters.isEmpty())
                    _charactersState.emit(StateUI.Error("There is no characters related to this movie"))
                else
                    _charactersState.emit(StateUI.Processed(_filmUI.value.characters.map { it.toSmallModel() }))
            }
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
                    _filmUI.value = filmUI.value.copy(
                        planets = filmUI.value.planets.plus(data)
                    )
                }
                if (_filmUI.value.planets.isEmpty())
                    _planetsState.emit(StateUI.Error("There is no planets related to this movie"))
                else
                    _planetsState.emit(StateUI.Processed(_filmUI.value.planets.map { it.toSmallModel() }))
            }
        }
    }

    fun favorite() {
        viewModelScope.launch {
            _filmUI.value.film?.let { film ->
                setFavoriteFilmsUseCase(film).collect { updatedFilm ->
                    _filmUI.value = filmUI.value.copy(film = updatedFilm)
                }
            }
        }
    }
}