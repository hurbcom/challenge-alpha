package com.vdemelo.starwarswiki.ui.species

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vdemelo.starwarswiki.domain.entity.RequestStatus
import com.vdemelo.starwarswiki.domain.entity.model.ItemsImageUrl
import com.vdemelo.starwarswiki.domain.entity.model.Species
import com.vdemelo.starwarswiki.domain.entity.model.SpeciesList
import com.vdemelo.starwarswiki.domain.entity.model.getImageUrl
import com.vdemelo.starwarswiki.domain.usecase.HomeUseCase
import kotlinx.coroutines.launch

class SpeciesViewModel(
    private val useCase: HomeUseCase
) : ViewModel() {

    private var currentPage = 0

    var speciesList = mutableStateOf<List<Species>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    init {
        loadSpeciesPaginated()
    }

    fun loadSpeciesPaginated() {
        viewModelScope.launch {
            val speciesRequestStatus: RequestStatus<SpeciesList> = useCase.fetchSpecies(currentPage)

            when (speciesRequestStatus) {
                is RequestStatus.Success -> {
                    endReached.value = (speciesRequestStatus.data?.next == null) //TODO ver se ele é null quando acabam os itens
                    val results = speciesRequestStatus.data?.results ?: listOf()
                    currentPage++

                    loadError.value = ""
                    isLoading.value = false
                    this@SpeciesViewModel.speciesList.value += results
                }

                is RequestStatus.Error -> {
                    loadError.value = speciesRequestStatus.message!!
                    isLoading.value = false
                }
            }
        }
    }

    // species. url = https://swapi.dev/api/species/1/
    // species image = https://starwars-visualguide.com/assets/img/species/1.jpg

    fun getSpeciesImageUrl(speciesUrl: String?): String? {
        return if (speciesUrl != null) {
            val speciesNumber = getSpeciesNumber(speciesUrl)
            ItemsImageUrl.SPECIES.getImageUrl(speciesNumber)
        } else {
            null
        }
    }

    private fun getSpeciesNumber(speciesUrl: String): String {
        val charsToDrop = if (speciesUrl.endsWith("/")) 1 else 0
        return speciesUrl.dropLast(charsToDrop).takeLastWhile { it.isDigit() }
    }

}
