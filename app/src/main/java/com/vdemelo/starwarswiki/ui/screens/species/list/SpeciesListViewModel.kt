package com.vdemelo.starwarswiki.ui.screens.species.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vdemelo.starwarswiki.domain.entity.RequestStatus
import com.vdemelo.starwarswiki.domain.entity.model.Species
import com.vdemelo.starwarswiki.domain.entity.model.SpeciesList
import com.vdemelo.starwarswiki.domain.usecase.ItemsUseCase
import com.vdemelo.starwarswiki.domain.usecase.SpeciesUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val STARTING_PAGE = 1

class SpeciesListViewModel(
    private val speciesUseCase: SpeciesUseCase,
    private val itemsUseCase: ItemsUseCase
) : ViewModel() {

    private var currentPage = STARTING_PAGE
    private var currentSearch: String? = null
    private var lastJob: Job? = null

    var speciesList = mutableStateOf<List<Species>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    init {
        loadSpeciesPaginated()
    }

    fun loadSpeciesPaginated(search: String? = null) {
        isLoading.value = true
        lastJob?.cancel()
        if (search != null && search != currentSearch) {
            currentSearch = search
            currentPage = STARTING_PAGE
            speciesList.value = listOf()
        }
        lastJob = viewModelScope.launch {
            delay(500L)
            when (
                val speciesRequestStatus: RequestStatus<SpeciesList> =
                    speciesUseCase.fetchSpecies(page = currentPage, search = currentSearch)
            ) {
                is RequestStatus.Success -> {
                    endReached.value = (speciesRequestStatus.data?.next == null)
                    val results = speciesRequestStatus.data?.results ?: listOf()
                    currentPage++

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
    }

    fun getSpeciesImageUrl(id: Int): String = itemsUseCase.getSpeciesImageUrl(id)
    fun getSpeciesNumber(speciesUrl: String): Int? = itemsUseCase.getItemId(speciesUrl)

}
