package br.com.gstoduto.starwars.navigation.specie

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.gstoduto.starwars.model.Specie
import br.com.gstoduto.starwars.navigation.DestinationsStarWarsApp
import br.com.gstoduto.starwars.ui.screens.specie.SpecieScreen
import br.com.gstoduto.starwars.ui.viewmodels.specie.SpecieViewModel

fun NavGraphBuilder.specieScreen(
    onNavigateToSpecieDetails: (Specie) -> Unit,
) {
    composable(DestinationsStarWarsApp.specieRoute.rota) {
        val viewModel = hiltViewModel<SpecieViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        SpecieScreen(
            uiState = uiState,
            columns = 2,
            onSpecieClick = onNavigateToSpecieDetails,
            onRetryLoadSpecies = {
                viewModel.loadSpecies()
            }
        )
    }
}

fun NavController.navigateToSpecie(
    navOptions: NavOptions? = null
) {
    navigate(DestinationsStarWarsApp.specieRoute.rota, navOptions)
}