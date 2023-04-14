package br.com.hurbandroidchallenge.presentation.screens.planet.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hurbandroidchallenge.data.remote.config.ApiUrls
import br.com.hurbandroidchallenge.domain.model.Planet
import br.com.hurbandroidchallenge.domain.model.base.PagedList
import br.com.hurbandroidchallenge.domain.use_case.GetPlanetsUseCase
import br.com.hurbandroidchallenge.presentation.model.StateUI
import br.com.hurbandroidchallenge.presentation.screens.planet.list.ui.PlanetsUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PlanetsViewModel(
    private val getPlanetsUseCase: GetPlanetsUseCase
) : ViewModel() {

    private val _planetsUI = mutableStateOf(PlanetsUI())
    val planetsUI: State<PlanetsUI> = _planetsUI

    private val _planetsState = MutableStateFlow<StateUI<PagedList<Planet>>>(StateUI.Idle())
    val planetsState = _planetsState.asStateFlow()

    private val _loadMoreState = MutableStateFlow<StateUI<PagedList<Planet>>>(StateUI.Idle())
    val loadMoreState = _loadMoreState.asStateFlow()

    init {
        loadPlanets()
    }

    private fun loadPlanets() {
        viewModelScope.launch {
            getPlanetsUseCase(url = ApiUrls.planets, clearLocalDatasource = false).onStart {
                _planetsState.emit(StateUI.Processing())
            }.catch {
                _planetsState.emit(StateUI.Error(it.message.orEmpty()))
            }.collect { data ->
                _planetsUI.value = planetsUI.value.copy(
                    planets = data.results,
                    nextPage = data.next
                )
                _planetsState.emit(StateUI.Processed(data))
            }
        }
    }

    fun loadMorePlanets() {
        viewModelScope.launch {
            _planetsUI.value.nextPage?.let { nextPage ->
                getPlanetsUseCase(url = nextPage, clearLocalDatasource = false).onStart {
                    _loadMoreState.emit(StateUI.Processing())
                }.catch {
                    _loadMoreState.emit(StateUI.Error(it.message.orEmpty()))
                }.collect { data ->
                    _planetsUI.value = planetsUI.value.copy(
                        planets = data.results,
                        nextPage = data.next
                    )
                    _loadMoreState.emit(StateUI.Processed(data))
                }
            }
        }
    }

}