package br.com.hurbandroidchallenge.presentation.screens.home.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.domain.model.Categories
import br.com.hurbandroidchallenge.presentation.compose.components.item_model.CategorySmallItems
import br.com.hurbandroidchallenge.presentation.compose.navigation.Screens
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoritesMainScreen(
    navHostController: NavHostController,
    viewModel: FavoritesViewModel = getViewModel(),
    update: MutableState<Boolean>,
) {
    LaunchedEffect(Unit) {
        if (update.value) {
            update.value = false
            viewModel.loadFavorites()
        }
    }
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Column(
            modifier = Modifier.padding(all = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Favoritos", style = MaterialTheme.typography.headlineMedium)
            CategorySmallItems(
                name = Categories.Characters.title,
                listState = viewModel.charactersState.collectAsState().value,
                onClick = { url ->
                    navHostController.navigate(Screens.CharacterDetail.routeWithArgument(url))
                }
            )
            CategorySmallItems(
                name = Categories.Films.title,
                listState = viewModel.filmsState.collectAsState().value,
                onClick = { url ->
                    navHostController.navigate(Screens.FilmDetail.routeWithArgument(url))
                }
            )
            CategorySmallItems(
                name = Categories.Planets.title,
                listState = viewModel.planetsState.collectAsState().value,
                onClick = { url ->
                    navHostController.navigate(Screens.PlanetDetail.routeWithArgument(url))
                }
            )
        }
    }
}