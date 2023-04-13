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
    private val getCharacterByUrlUseCase: GetCharacterByUrlUseCase
) : ViewModel() {
    
    private val _filmDetailUI = mutableStateOf(FilmDetailUI())
    val filmDetailUI: State<FilmDetailUI> = _filmDetailUI
    
    private val _filmsState = MutableStateFlow<StateUI<Film>>(StateUI.Idle())
    val filmsState = _filmsState.asStateFlow()

    private val _characterState = MutableStateFlow<StateUI<List<People>>>(StateUI.Idle())
    val characterState = _characterState.asStateFlow()

    fun loadFilms(url: String) {
        viewModelScope.launch {
            getFilmByUrlUseCase(url, fromRemote = false).onStart {
                _filmsState.emit(StateUI.Processing())
            }.catch {
                _filmsState.emit(StateUI.Error(it.message.orEmpty()))
            }.collect { data ->
                _filmDetailUI.value = filmDetailUI.value.copy(
                    film = data
                )
                _filmsState.emit(StateUI.Processed(data))
                loadCharacters(data.characters)
            }
        }
    }

    private fun loadCharacters(urls: List<String>) {
        viewModelScope.launch {
            urls.forEach { urls ->
                getCharacterByUrlUseCase(urls, fromRemote = true).onStart {
                    _characterState.emit(StateUI.Processing())
                }.catch {
                    _characterState.emit(StateUI.Error())
                }.collect { data ->
                    _filmDetailUI.value = filmDetailUI.value.copy(
                        characters = filmDetailUI.value.characters.plus(data)
                    )
                }
            }
            _characterState.emit(StateUI.Processed(_filmDetailUI.value.characters))
        }
    }
    
    
    
}