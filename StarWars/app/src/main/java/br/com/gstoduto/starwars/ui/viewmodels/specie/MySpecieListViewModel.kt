package br.com.gstoduto.starwars.ui.viewmodels.specie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gstoduto.starwars.database.entities.toSpecie
import br.com.gstoduto.starwars.model.Specie
import br.com.gstoduto.starwars.ui.uistates.specie.MySpecieListUiState
import br.com.gstoduto.starwars.ui.use_case.specie.GetMySpecieListUseCaseImpl
import br.com.gstoduto.starwars.ui.use_case.specie.RemoveSpecieFromMyListUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MySpecieListViewModel @Inject constructor(
    private val getMySpecieListUseCase: GetMySpecieListUseCaseImpl,
    private val removeSpecieFromMyListUseCase: RemoveSpecieFromMyListUseCaseImpl
) : ViewModel() {

    private val _uiState = MutableStateFlow<MySpecieListUiState>(
        MySpecieListUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    var species = mutableListOf<Specie>()

    init {
        viewModelScope.launch {
            loadMyList()
        }
    }

    private suspend fun loadUiState() {
        getMySpecieListUseCase.getMyListSpecies().collect {
            species.clear()
            it.listIterator().forEach { specieEntity ->
                species.add(specieEntity.toSpecie())
            }
            _uiState.update {
                if (species.isEmpty()) {
                    MySpecieListUiState.Empty(species = species)
                } else {
                    MySpecieListUiState.Success(species = species)
                }
            }
        }
    }

    suspend fun removeFromMyList(specie: Specie) {
        removeSpecieFromMyListUseCase.removeSpecieFromMyList(specie.id.toString())
        species.remove(specie)
        val newState = if (species.isEmpty()) {
            MySpecieListUiState.Empty(species = species)
        } else {
            MySpecieListUiState.Success(species = species)
        }

        if (newState != _uiState.value) {
            _uiState.value = newState
        }
    }

    private suspend fun loadMyList() {
        loadUiState()
    }

}