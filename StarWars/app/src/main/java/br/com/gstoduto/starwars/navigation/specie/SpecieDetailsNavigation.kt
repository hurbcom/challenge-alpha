package br.com.gstoduto.starwars.navigation.specie

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.gstoduto.starwars.navigation.DestinationsStarWarsApp
import br.com.gstoduto.starwars.ui.screens.specie.SpecieDetailsScreen
import br.com.gstoduto.starwars.ui.viewmodels.specie.SpecieDetailsViewModel
import br.com.gstoduto.starwars.util.Constants.ID_SPECIE
import kotlinx.coroutines.launch

fun NavGraphBuilder.specieDetails(
    savedStateHandle: SavedStateHandle,
) {
    composable(
        route = DestinationsStarWarsApp.SpecieDetails.routeWithArguments,
        arguments = DestinationsStarWarsApp.SpecieDetails.arguments
    ) { navBackStackEntry ->
        navBackStackEntry.arguments?.getString(
            ID_SPECIE
        )?.let { idSpecie ->

            val viewModel = hiltViewModel<SpecieDetailsViewModel>()
            val uiState by viewModel.uiState.collectAsState()

            val scope = rememberCoroutineScope()
            savedStateHandle[ID_SPECIE] = idSpecie

            SpecieDetailsScreen(
                uiState = uiState,
                onAddSpecieToMyListClick = {
                    scope.launch {
                        viewModel.addSpecieToMyList(it)
                    }
                },
                onRemoveSpecieFromMyList = {
                    scope.launch {
                        viewModel.removeSpecieFromMyList(it)
                    }
                }
            )
        }
    }
}