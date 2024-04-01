package br.com.gstoduto.starwars.ui.viewmodels.specie

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gstoduto.starwars.database.entities.toSpecie
import br.com.gstoduto.starwars.model.Specie
import br.com.gstoduto.starwars.ui.uistates.specie.SpecieDetailsUiState
import br.com.gstoduto.starwars.ui.use_case.specie.AddSpecieToMyListUseCaseImpl
import br.com.gstoduto.starwars.ui.use_case.specie.GetSpecieUseCaseImpl
import br.com.gstoduto.starwars.ui.use_case.specie.RemoveSpecieFromMyListUseCaseImpl
import br.com.gstoduto.starwars.util.Constants.ID_SPECIE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpecieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSpecieUseCase: GetSpecieUseCaseImpl,
    private val addSpecieToMyListUseCase: AddSpecieToMyListUseCaseImpl,
    private val removeSpecieFromMyListUseCase: RemoveSpecieFromMyListUseCaseImpl
) : ViewModel() {

    private val idVehicle = savedStateHandle.get<String>(ID_SPECIE)

    private val _uiState = MutableStateFlow(SpecieDetailsUiState())
    val uiState: StateFlow<SpecieDetailsUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            idVehicle?.let { loadSpecie(it) }
        }
    }

    private suspend fun loadSpecie(id: String) {
        val specie = getSpecieUseCase.findSpecie(id)
        specie.let {
            it.collect { specieEntity ->
                _uiState.value = _uiState.value.copy(
                    specie = specieEntity?.toSpecie()
                )
            }
        }
    }

    suspend fun addSpecieToMyList(specie: Specie) {
        addSpecieToMyListUseCase.addSpecieToMyList(specie.id.toString())
    }

    suspend fun removeSpecieFromMyList(specie: Specie) {
        removeSpecieFromMyListUseCase.removeSpecieFromMyList(specie.id.toString())
    }
}
