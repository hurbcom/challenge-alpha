package br.com.gstoduto.starwars.navigation.vehicle

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.gstoduto.starwars.model.Vehicle
import br.com.gstoduto.starwars.navigation.DestinationsStarWarsApp
import br.com.gstoduto.starwars.ui.screens.vehicle.VehicleScreen
import br.com.gstoduto.starwars.ui.viewmodels.vehicle.VehicleViewModel

fun NavGraphBuilder.vehicleScreen(
    onNavigateToVehicleDetails: (Vehicle) -> Unit,
) {
    composable(DestinationsStarWarsApp.vehicleRoute.rota) {
        val viewModel = hiltViewModel<VehicleViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        VehicleScreen(
            uiState = uiState,
            columns = 2,
            onVehicleClick = onNavigateToVehicleDetails,
            onRetryLoadVehicles = {
                viewModel.loadVehicles()
            }
        )
    }
}

fun NavController.navigateToVehicle(
    navOptions: NavOptions? = null
) {
    navigate(DestinationsStarWarsApp.vehicleRoute.rota, navOptions)
}