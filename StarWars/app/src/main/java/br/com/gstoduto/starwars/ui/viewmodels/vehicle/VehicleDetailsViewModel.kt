package br.com.gstoduto.starwars.ui.viewmodels.vehicle

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gstoduto.starwars.database.entities.toVehicle
import br.com.gstoduto.starwars.model.Vehicle
import br.com.gstoduto.starwars.ui.uistates.vehicle.VehicleDetailsUiState
import br.com.gstoduto.starwars.ui.use_case.vehicle.AddVehicleToMyListUseCaseImpl
import br.com.gstoduto.starwars.ui.use_case.vehicle.GetVehicleUseCaseImpl
import br.com.gstoduto.starwars.ui.use_case.vehicle.RemoveVehicleFromMyListUseCaseImpl
import br.com.gstoduto.starwars.util.Constants.ID_VEHICLE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getVehicleUseCase: GetVehicleUseCaseImpl,
    private val addVehicleToMyListUseCase: AddVehicleToMyListUseCaseImpl,
    private val removeVehicleFromMyListUseCase: RemoveVehicleFromMyListUseCaseImpl
) : ViewModel() {

    private val idVehicle = savedStateHandle.get<String>(ID_VEHICLE)

    private val _uiState = MutableStateFlow(VehicleDetailsUiState())
    val uiState: StateFlow<VehicleDetailsUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            idVehicle?.let { loadVehicle(it) }
        }
    }

    private suspend fun loadVehicle(id: String) {
        val vehicle = getVehicleUseCase.findVehicle(id)
        vehicle.let {
            vehicle.collect { vehicleEntity ->
                _uiState.value = _uiState.value.copy(
                    vehicle = vehicleEntity?.toVehicle()
                )
            }
        }
    }

    suspend fun addVehicleToMyList(vehicle: Vehicle) {
        addVehicleToMyListUseCase.addVehicleToMyList(vehicle.id.toString())
    }

    suspend fun removeVehicleFromMyList(vehicle: Vehicle) {
        removeVehicleFromMyListUseCase.removeVehicleFromMyList(vehicle.id.toString())
    }
}
