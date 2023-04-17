package br.com.hurbandroidchallenge.presentation.screens.home.favorites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hurbandroidchallenge.commom.extension.symmetricDifference
import br.com.hurbandroidchallenge.data.mapper.characters.toSmallModel
import br.com.hurbandroidchallenge.data.mapper.films.toSmallModel
import br.com.hurbandroidchallenge.data.mapper.planets.toSmallModel
import br.com.hurbandroidchallenge.domain.use_case.characters.GetFavoriteCharactersUseCase
import br.com.hurbandroidchallenge.domain.use_case.films.GetFavoriteFilmsUseCase
import br.com.hurbandroidchallenge.domain.use_case.planets.GetFavoritesPlanetsUseCase
import br.com.hurbandroidchallenge.presentation.model.SmallItemModel
import br.com.hurbandroidchallenge.presentation.model.StateUI
import br.com.hurbandroidchallenge.presentation.screens.home.favorites.ui.FavoritesUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getFavoritesPlanetsUseCase: GetFavoritesPlanetsUseCase,
    private val getFavoriteFilmsUseCase: GetFavoriteFilmsUseCase,
    private val getFavoriteCharactersUseCase: GetFavoriteCharactersUseCase,
) : ViewModel() {

    private val _favoritesUI = mutableStateOf(FavoritesUI())
    val favoritesUI: State<FavoritesUI> = _favoritesUI

    private val _charactersState = MutableStateFlow<StateUI<List<SmallItemModel>>>(StateUI.Idle())
    val charactersState = _charactersState.asStateFlow()

    private val _filmsState = MutableStateFlow<StateUI<List<SmallItemModel>>>(StateUI.Idle())
    val filmsState = _filmsState.asStateFlow()

    private val _planetsState = MutableStateFlow<StateUI<List<SmallItemModel>>>(StateUI.Idle())
    val planetsState = _planetsState.asStateFlow()

    fun loadFavorites() {
        loadCharacters()
        loadFilms()
        loadPlanets()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            getFavoriteCharactersUseCase().onStart {
                _charactersState.emit(StateUI.Processing())
            }.catch {
                _charactersState.emit(StateUI.Error(it.message.orEmpty()))
            }.collect { data ->
                val models = data.map { it.toSmallModel() }
                val hasDifference =
                    (models symmetricDifference _favoritesUI.value.characters).isNotEmpty()
                if (hasDifference) {
                    _favoritesUI.value = favoritesUI.value.copy(
                        characters = models
                    )
                }
                if (favoritesUI.value.characters.isEmpty()) {
                    _charactersState.emit(StateUI.Error("You have no favorites characters"))
                } else {
                    _charactersState.emit(StateUI.Processed(models))
                }
            }
        }
    }

    private fun loadFilms() {
        viewModelScope.launch {
            getFavoriteFilmsUseCase().onStart {
                _filmsState.emit(StateUI.Processing())
            }.catch {
                _filmsState.emit(StateUI.Error(it.message.orEmpty()))
            }.collect { data ->
                val models = data.map { it.toSmallModel() }
                val hasDifference =
                    (models symmetricDifference _favoritesUI.value.films).isNotEmpty()
                if (hasDifference) {
                    _favoritesUI.value = favoritesUI.value.copy(
                        films = models
                    )
                }
                if (favoritesUI.value.films.isEmpty()) {
                    _filmsState.emit(StateUI.Error("You have no favorites planets"))
                } else {
                    _filmsState.emit(StateUI.Processed(models))
                }
            }
        }
    }

    private fun loadPlanets() {
        viewModelScope.launch {
            getFavoritesPlanetsUseCase().onStart {
                _planetsState.emit(StateUI.Processing())
            }.catch {
                _planetsState.emit(StateUI.Error(it.message.orEmpty()))
            }.collect { data ->
                val models = data.map { it.toSmallModel() }
                val hasDifference =
                    (models symmetricDifference _favoritesUI.value.planets).isNotEmpty()
                if (hasDifference) {
                    _favoritesUI.value = favoritesUI.value.copy(
                        planets = models
                    )
                }
                if (favoritesUI.value.planets.isEmpty()) {
                    _planetsState.emit(StateUI.Error("You have no favorites planets"))
                } else {
                    _planetsState.emit(StateUI.Processed(models))
                }
            }
        }
    }

}