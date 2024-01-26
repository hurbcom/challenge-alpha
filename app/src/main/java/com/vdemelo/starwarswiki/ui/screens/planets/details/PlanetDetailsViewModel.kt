package com.vdemelo.starwarswiki.ui.screens.planets.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vdemelo.starwarswiki.domain.entity.RequestStatus
import com.vdemelo.starwarswiki.domain.entity.model.Planet
import com.vdemelo.starwarswiki.domain.usecase.ItemsUseCase
import com.vdemelo.starwarswiki.domain.usecase.PlanetsUseCase
import kotlinx.coroutines.launch

class PlanetDetailsViewModel (
    private val planetsUseCase: PlanetsUseCase,
    private val itemsUseCase: ItemsUseCase
) : ViewModel() {

    var planetDetails = mutableStateOf<Planet?>(null)
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(true)

    fun loadPlanetDetails(id: Int) {
        isLoading.value = true
        viewModelScope.launch {
            when (
                val planetRequestStatus: RequestStatus<Planet> =
                    planetsUseCase.fetchPlanetDetails(id)
            ) {
                is RequestStatus.Success -> {
                    loadError.value = ""
                    isLoading.value = false
                    this@PlanetDetailsViewModel.planetDetails.value = planetRequestStatus.data
                }

                is RequestStatus.Error -> {
                    loadError.value = planetRequestStatus.message!!
                    isLoading.value = false
                }
            }
        }
    }

    fun getPlanetImageUrl(id: Int): String = itemsUseCase.getPlanetImageUrl(id)

}
