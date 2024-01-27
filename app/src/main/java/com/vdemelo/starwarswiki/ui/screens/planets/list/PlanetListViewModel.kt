package com.vdemelo.starwarswiki.ui.screens.planets.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vdemelo.starwarswiki.domain.entity.RequestStatus
import com.vdemelo.starwarswiki.domain.entity.model.Planet
import com.vdemelo.starwarswiki.domain.entity.model.PlanetsList
import com.vdemelo.starwarswiki.domain.usecase.ItemsUseCase
import com.vdemelo.starwarswiki.domain.usecase.PlanetsUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val STARTING_PAGE = 1

class PlanetListViewModel(
    private val planetsUseCase: PlanetsUseCase,
    private val itemsUseCase: ItemsUseCase
) : ViewModel() {

    private var currentPage = STARTING_PAGE
    private var currentSearch: String? = null
    private var lastJob: Job? = null

    var list = mutableStateOf<List<Planet>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    init {
        loadPlanetsPaginated()
    }

    fun loadPlanetsPaginated(search: String? = null) {
        isLoading.value = true
        lastJob?.cancel()
        if (search != null && search != currentSearch) {
            currentSearch = search
            currentPage = STARTING_PAGE
            list.value = listOf()
        }
        lastJob = viewModelScope.launch {
            delay(500L)
            when (
                val planetRequestStatus: RequestStatus<PlanetsList> =
                    planetsUseCase.fetchPlanets(page = currentPage, search = currentSearch)
            ) {
                is RequestStatus.Success -> {
                    endReached.value = (planetRequestStatus.data?.next == null)
                    val results = planetRequestStatus.data?.results ?: listOf()
                    currentPage++

                    loadError.value = ""
                    isLoading.value = false
                    this@PlanetListViewModel.list.value += results
                }

                is RequestStatus.Error -> {
                    loadError.value = planetRequestStatus.message!!
                    isLoading.value = false
                }
            }
        }
    }

    fun getPlanetImageUrl(id: Int): String = itemsUseCase.getPlanetImageUrl(id)
    fun getPlanetId(url: String): Int? = itemsUseCase.getItemId(url)

}
