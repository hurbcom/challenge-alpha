package br.com.gstoduto.starwars.ui.viewmodels.vehicle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gstoduto.starwars.database.entities.toVehicle
import br.com.gstoduto.starwars.model.Vehicle
import br.com.gstoduto.starwars.ui.uistates.vehicle.MyVehicleListUiState
import br.com.gstoduto.starwars.ui.use_case.vehicle.GetMyVehicleListUseCaseImpl
import br.com.gstoduto.starwars.ui.use_case.vehicle.RemoveVehicleFromMyListUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyVehicleListViewModel @Inject constructor(
    private val getMyVehicleListUseCase: GetMyVehicleListUseCaseImpl,
    private val removeVehicleFromMyListUseCase: RemoveVehicleFromMyListUseCaseImpl
) : ViewModel() {

    private val _uiState = MutableStateFlow<MyVehicleListUiState>(
        MyVehicleListUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    var vehicles = mutableListOf<Vehicle>()

    init {
        viewModelScope.launch {
            loadMyList()
        }
    }

    private suspend fun loadUiState() {
        getMyVehicleListUseCase.getMyListVehicles().collect {
            vehicles.clear()
            it.listIterator().forEach { vehicleEntity ->
                vehicles.add(vehicleEntity.toVehicle())
            }
            _uiState.update {
                if (vehicles.isEmpty()) {
                    MyVehicleListUiState.Empty(vehicles = vehicles)
                } else {
                    MyVehicleListUiState.Success(vehicles = vehicles)
                }
            }
        }
    }

    suspend fun removeFromMyList(vehicle: Vehicle) {
        removeVehicleFromMyListUseCase.removeVehicleFromMyList(vehicle.id.toString())
        vehicles.remove(vehicle)
        val newState = if (vehicles.isEmpty()) {
            MyVehicleListUiState.Empty(vehicles = vehicles)
        } else {
            MyVehicleListUiState.Success(vehicles = vehicles)
        }

        if (newState != _uiState.value) {
            _uiState.value = newState
        }
    }

    suspend fun loadMyList() {
        loadUiState()
    }

}