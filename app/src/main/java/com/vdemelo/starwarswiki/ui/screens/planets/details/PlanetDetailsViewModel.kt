package com.vdemelo.starwarswiki.ui.screens.planets.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vdemelo.starwarswiki.domain.entity.RequestStatus
import com.vdemelo.starwarswiki.domain.entity.model.Species
import com.vdemelo.starwarswiki.domain.usecase.ItemsUseCase
import com.vdemelo.starwarswiki.domain.usecase.PlanetsUseCase
import kotlinx.coroutines.launch

class PlanetDetailsViewModel (
    private val planetsUseCase: PlanetsUseCase,
    private val itemsUseCase: ItemsUseCase
) : ViewModel() {

    var speciesDetails = mutableStateOf<Species?>(null)
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(true)

    fun loadSpeciesDetails(id: Int) {
        viewModelScope.launch {
            when (
                val speciesRequestStatus: RequestStatus<Species> =
                    planetsUseCase.fetchPlanetDetails(id)
            ) {
                is RequestStatus.Success -> {
                    loadError.value = ""
                    isLoading.value = false
                    this@PlanetDetailsViewModel.speciesDetails.value = speciesRequestStatus.data
                }

                is RequestStatus.Error -> {
                    loadError.value = speciesRequestStatus.message!!
                    isLoading.value = false
                }
            }
        }
    }

    fun getSpeciesImageUrl(id: Int): String = itemsUseCase.getSpeciesImageUrl(id)

}
