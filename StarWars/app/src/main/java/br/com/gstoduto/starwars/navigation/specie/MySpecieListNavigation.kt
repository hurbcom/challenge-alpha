package br.com.gstoduto.starwars.navigation.specie

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
import br.com.gstoduto.starwars.model.Specie
import br.com.gstoduto.starwars.navigation.DestinationsStarWarsApp
import br.com.gstoduto.starwars.ui.screens.specie.MySpecieListScreen
import br.com.gstoduto.starwars.ui.viewmodels.specie.MySpecieListViewModel
import kotlinx.coroutines.launch

fun NavGraphBuilder.mySpecieListScreen(
    onNavigateToSpecie: () -> Unit,
    onNavigateToSpecieDetails: (Specie) -> Unit
) {
    composable(DestinationsStarWarsApp.mySpecieListRoute.rota) {
        val viewModel = hiltViewModel<MySpecieListViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        val refreshKey = remember { mutableIntStateOf(0) }
        val scope = rememberCoroutineScope()

        MySpecieListScreen(
            uiState = uiState,
            onSeeOtherSpecies = onNavigateToSpecie,
            onRemoveSpecieFromMyList = {
                scope.launch {
                    viewModel.removeFromMyList(it)
                    refreshKey.value++
                }
            },
            onSpecieClick = onNavigateToSpecieDetails,
            refreshKey = refreshKey.value,
        )
    }
}

fun NavController.navigateToMySpecieList(
    navOptions: NavOptions? = null,
) {
    navigate(DestinationsStarWarsApp.mySpecieListRoute.rota, navOptions)
}