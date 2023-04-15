package br.com.hurbandroidchallenge.presentation.screens.home.last_seen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hurbandroidchallenge.data.mapper.characters.toSmallModel
import br.com.hurbandroidchallenge.data.mapper.films.toSmallModel
import br.com.hurbandroidchallenge.data.mapper.planets.toSmallMode
import br.com.hurbandroidchallenge.domain.use_case.characters.GetLastSeenCharactersUseCase
import br.com.hurbandroidchallenge.domain.use_case.films.GetLastSeenFilmsUseCase
import br.com.hurbandroidchallenge.domain.use_case.planets.GetLastSeenPlanetsUseCase
import br.com.hurbandroidchallenge.presentation.model.SmallItemModel
import br.com.hurbandroidchallenge.presentation.model.StateUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class LastSeenViewModel(
    private val getLastSeenPlanetsUseCase: GetLastSeenPlanetsUseCase,
    private val getLastSeenCharactersUseCase: GetLastSeenCharactersUseCase,
    private val getLastSeenFilmsUseCase: GetLastSeenFilmsUseCase,
) : ViewModel() {

    private val _charactersState = MutableStateFlow<StateUI<List<SmallItemModel>>>(StateUI.Idle())
    val charactersState = _charactersState.asStateFlow()

    private val _filmsState = MutableStateFlow<StateUI<List<SmallItemModel>>>(StateUI.Idle())
    val filmsState = _filmsState.asStateFlow()

    private val _planetsState = MutableStateFlow<StateUI<List<SmallItemModel>>>(StateUI.Idle())
    val planetsState = _planetsState.asStateFlow()

    fun loadLastSeen() {
        loadCharacters()
        loadFilms()
        loadPlanets()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            getLastSeenCharactersUseCase().onStart {
                _charactersState.emit(StateUI.Processing())
            }.catch {
                _charactersState.emit(StateUI.Error(it.message.orEmpty()))
            }.collect { data ->
                if (data.isEmpty())
                    _charactersState.emit(StateUI.Error("You didn't see any characters"))
                else
                    _charactersState.emit(StateUI.Processed(data.map { it.toSmallModel() }))
            }
        }
    }

    private fun loadFilms() {
        viewModelScope.launch {
            getLastSeenFilmsUseCase().onStart {
                _filmsState.emit(StateUI.Processing())
            }.catch {
                _filmsState.emit(StateUI.Error(it.message.orEmpty()))
            }.collect { data ->
                if (data.isEmpty())
                    _filmsState.emit(StateUI.Error("You didn't see any films"))
                else
                    _filmsState.emit(StateUI.Processed(data.map { it.toSmallModel() }))
            }
        }
    }

    private fun loadPlanets() {
        viewModelScope.launch {
            getLastSeenPlanetsUseCase().onStart {
                _planetsState.emit(StateUI.Processing())
            }.catch {
                _planetsState.emit(StateUI.Error(it.message.orEmpty()))
            }.collect { data ->
                if (data.isEmpty())
                    _planetsState.emit(StateUI.Error("You didn't see any planets"))
                else
                    _planetsState.emit(StateUI.Processed(data.map { it.toSmallMode() }))
            }
        }
    }

}