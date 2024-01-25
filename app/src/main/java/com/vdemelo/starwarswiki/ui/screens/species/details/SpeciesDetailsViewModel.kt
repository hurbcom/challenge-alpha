package com.vdemelo.starwarswiki.ui.screens.species.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vdemelo.starwarswiki.domain.entity.RequestStatus
import com.vdemelo.starwarswiki.domain.entity.model.Species
import com.vdemelo.starwarswiki.domain.usecase.SpeciesUseCase
import kotlinx.coroutines.launch

class SpeciesDetailsViewModel (
    private val useCase: SpeciesUseCase
) : ViewModel() {

    var speciesDetails = mutableStateOf<Species?>(null)
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(true)

    fun loadSpeciesDetails(speciesNumber: Int) {
        viewModelScope.launch {
            when (
                val speciesRequestStatus: RequestStatus<Species> =
                    useCase.fetchSpeciesDetails(speciesNumber)
            ) {
                is RequestStatus.Success -> {
                    loadError.value = ""
                    isLoading.value = false
                    this@SpeciesDetailsViewModel.speciesDetails.value = speciesRequestStatus.data
                }

                is RequestStatus.Error -> {
                    loadError.value = speciesRequestStatus.message!!
                    isLoading.value = false
                }
            }
        }
    }

    fun getSpeciesImageUrl(speciesNumber: Int): String = useCase.getSpeciesImageUrl(speciesNumber)
    //fun getSpeciesNumber(speciesUrl: String): Int? = useCase.getSpeciesNumber(speciesUrl) //TODO acho q n vai usar

}
