package com.vdemelo.starwarswiki.ui.screens.species.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vdemelo.starwarswiki.domain.entity.RequestStatus
import com.vdemelo.starwarswiki.domain.entity.model.Species
import com.vdemelo.starwarswiki.domain.entity.model.SpeciesList
import com.vdemelo.starwarswiki.domain.usecase.SpeciesUseCase
import kotlinx.coroutines.launch

class SpeciesListViewModel(
    private val useCase: SpeciesUseCase
) : ViewModel() {

    private var currentPage = 0

    var speciesList = mutableStateOf<List<Species>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    init {
        loadSpeciesPaginated()
    }

    //TODO mecanismo de busca n ta feito, tem q fazer :)

    fun loadSpeciesPaginated() {
        viewModelScope.launch {
            when (
                val speciesRequestStatus: RequestStatus<SpeciesList> =
                    useCase.fetchSpecies(currentPage) //TODO logica aqui retorna sempre a mesma coisa e n ta mostrando o loading
            ) {
                is RequestStatus.Success -> {
                    endReached.value =
                        (speciesRequestStatus.data?.next == null) //TODO ver se ele é null quando acabam os itens
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

    fun getSpeciesImageUrl(speciesNumber: Int): String = useCase.getSpeciesImageUrl(speciesNumber)
    fun getSpeciesNumber(speciesUrl: String): Int? = useCase.getSpeciesNumber(speciesUrl)

}
