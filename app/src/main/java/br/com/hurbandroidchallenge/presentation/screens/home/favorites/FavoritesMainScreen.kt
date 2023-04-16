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
    val favoritesUI = viewModel.favoritesUI.value
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Column(
            modifier = Modifier.padding(all = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Favorites", style = MaterialTheme.typography.headlineSmall)
            CategorySmallItems(
                name = Categories.Characters.title,
                itemList = favoritesUI.characters,
                onClick = { url ->
                    navHostController.navigate(Screens.CharacterDetail.routeWithArgument(url))
                },
                listState = viewModel.charactersState.collectAsState().value
            )
            CategorySmallItems(
                name = Categories.Films.title,
                itemList = favoritesUI.films,
                onClick = { url ->
                    navHostController.navigate(Screens.FilmDetail.routeWithArgument(url))
                },
                listState = viewModel.filmsState.collectAsState().value
            )
            CategorySmallItems(
                name = Categories.Planets.title,
                itemList = favoritesUI.planets,
                onClick = { url ->
                    navHostController.navigate(Screens.PlanetDetail.routeWithArgument(url))
                },
                listState = viewModel.planetsState.collectAsState().value
            )
        }
    }
}