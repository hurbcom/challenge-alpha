package br.com.hurbandroidchallenge.presentation.screens.film.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hurbandroidchallenge.domain.model.Film
import br.com.hurbandroidchallenge.domain.model.People
import br.com.hurbandroidchallenge.domain.use_case.GetCharacterByUrlUseCase
import br.com.hurbandroidchallenge.domain.use_case.GetFilmByUrlUseCase
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
    url: String
) : ViewModel() {
    
    private val _filmDetailUI = mutableStateOf(FilmDetailUI())
    val filmUI: State<FilmDetailUI> = _filmDetailUI
    
    private val _filmState = MutableStateFlow<StateUI<Film>>(StateUI.Idle())
    val filmState = _filmState.asStateFlow()

    private val _charactersState = MutableStateFlow<StateUI<List<People>>>(StateUI.Idle())
    val charactersState = _charactersState.asStateFlow()

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
            }
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
                    _filmDetailUI.value = filmUI.value.copy(
                        characters = filmUI.value.characters.plus(data)
                    )
                }
            }
            _charactersState.emit(StateUI.Processed(_filmDetailUI.value.characters))
        }
    }
}