package br.com.gstoduto.starwars.navigation.vehicle

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.gstoduto.starwars.model.Vehicle
import br.com.gstoduto.starwars.navigation.DestinationsStarWarsApp
import br.com.gstoduto.starwars.ui.screens.vehicle.MyVehicleListScreen
import br.com.gstoduto.starwars.ui.viewmodels.vehicle.MyVehicleListViewModel
import kotlinx.coroutines.launch

fun NavGraphBuilder.myVehicleListScreen(
    onNavigateToVehicle: () -> Unit,
    onNavigateToVehicleDetails: (Vehicle) -> Unit
) {
    composable(DestinationsStarWarsApp.myVehicleListRoute.rota) {
        val viewModel = hiltViewModel<MyVehicleListViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        val refreshKey = remember { mutableIntStateOf(0) }
        val scope = rememberCoroutineScope()

        MyVehicleListScreen(
            uiState = uiState,
            onSeeOtherVehicles = onNavigateToVehicle,
            onRemoveVehicleFromMyList = {
                scope.launch {
                    viewModel.removeFromMyList(it)
                    refreshKey.value++
                }
            },
            onVehicleClick = onNavigateToVehicleDetails,
            refreshKey = refreshKey.value,
        )
    }
}

fun NavController.navigateToMyVehicleList(
    navOptions: NavOptions? = null,
) {
    navigate(DestinationsStarWarsApp.myVehicleListRoute.rota, navOptions)
}