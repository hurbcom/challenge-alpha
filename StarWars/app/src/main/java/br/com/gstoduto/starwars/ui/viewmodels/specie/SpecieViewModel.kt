package br.com.gstoduto.starwars.ui.viewmodels.specie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gstoduto.starwars.ui.uistates.specie.SpecieUiState
import br.com.gstoduto.starwars.ui.use_case.specie.GetSpeciesUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpecieViewModel @Inject constructor(
    private val getSpeciesUseCase: GetSpeciesUseCaseImpl
) : ViewModel() {

    private var currentUiStateJob: Job? = null
    private val _uiState = MutableStateFlow<SpecieUiState>(
        SpecieUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    init {
        loadUiState()
    }

    private fun loadUiState() {
        currentUiStateJob?.cancel()
        currentUiStateJob = viewModelScope.launch {
            getSpeciesUseCase.findSpecies().onStart {
                _uiState.update { SpecieUiState.Loading }
            }.collectLatest { species ->
                if (species.isEmpty()) {
                    _uiState.update {
                        SpecieUiState.Empty
                    }
                } else {
                    _uiState.update {
                        SpecieUiState.Success(
                            species = species
                        )
                    }
                }
            }
        }
    }

    fun loadSpecies() {
        loadUiState()
    }
}