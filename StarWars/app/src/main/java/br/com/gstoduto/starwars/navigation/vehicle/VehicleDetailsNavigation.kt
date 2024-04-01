package br.com.gstoduto.starwars.navigation.vehicle

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.gstoduto.starwars.navigation.DestinationsStarWarsApp
import br.com.gstoduto.starwars.ui.screens.vehicle.VehicleDetailsScreen
import br.com.gstoduto.starwars.ui.viewmodels.vehicle.VehicleDetailsViewModel
import br.com.gstoduto.starwars.util.Constants.ID_VEHICLE
import kotlinx.coroutines.launch

fun NavGraphBuilder.vehicleDetails(
    savedStateHandle: SavedStateHandle,
) {
    composable(
        route = DestinationsStarWarsApp.VehicleDetails.routeWithArguments,
        arguments = DestinationsStarWarsApp.VehicleDetails.arguments
    ) { navBackStackEntry ->
        navBackStackEntry.arguments?.getString(
            ID_VEHICLE
        )?.let { idVehicle ->

            val viewModel = hiltViewModel<VehicleDetailsViewModel>()
            val uiState by viewModel.uiState.collectAsState()

            val scope = rememberCoroutineScope()
            savedStateHandle[ID_VEHICLE] = idVehicle

            VehicleDetailsScreen(
                uiState = uiState,
                onAddVehicleToMyListClick = {
                    scope.launch {
                        viewModel.addVehicleToMyList(it)
                    }
                },
                onRemoveVehicleFromMyList = {
                    scope.launch {
                        viewModel.removeVehicleFromMyList(it)
                    }
                }
            )
        }
    }
}