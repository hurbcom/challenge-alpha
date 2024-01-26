package com.vdemelo.starwarswiki.ui.screens.species.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vdemelo.starwarswiki.domain.entity.RequestStatus
import com.vdemelo.starwarswiki.domain.entity.model.Species
import com.vdemelo.starwarswiki.domain.entity.model.SpeciesList
import com.vdemelo.starwarswiki.domain.usecase.SpeciesUseCase
import kotlinx.coroutines.launch

private const val STARTING_PAGE = 1

class SpeciesListViewModel(
    private val useCase: SpeciesUseCase
) : ViewModel() {

    private var currentPage = STARTING_PAGE
    private var currentSearch: String? = null

    var speciesList = mutableStateOf<List<Species>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    init {
        loadSpeciesPaginated()
    }

    fun loadSpeciesPaginated(search: String? = null) {
        if (search != null && search != currentSearch) {
            currentSearch = search
            currentPage = STARTING_PAGE
            speciesList.value = listOf()
        }
        viewModelScope.launch {
            when (
                val speciesRequestStatus: RequestStatus<SpeciesList> =
                    useCase.fetchSpecies(page = currentPage, search = currentSearch)
            ) {
                is RequestStatus.Success -> {
                    endReached.value = (speciesRequestStatus.data?.next == null)
                    val results = speciesRequestStatus.data?.results ?: listOf()
                    currentPage++ //TODO has a bug that it keeps requesting after endReached

                    loadError.value = ""
                    isLoading.value = false
                    this@SpeciesListViewModel.speciesList.value += results
                }

                is RequestStatus.Error -> {
                    loadError.value = speciesRequestStatus.message!!
                    isLoading.value = false
                }
            }
        }
        //TODO n ta mostrando o loading
    }

    fun getSpeciesImageUrl(speciesNumber: Int): String = useCase.getSpeciesImageUrl(speciesNumber)
    fun getSpeciesNumber(speciesUrl: String): Int? = useCase.getSpeciesNumber(speciesUrl)

}
