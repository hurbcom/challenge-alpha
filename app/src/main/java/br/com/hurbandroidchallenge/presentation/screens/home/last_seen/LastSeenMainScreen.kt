package br.com.hurbandroidchallenge.presentation.screens.home.last_seen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.domain.model.Categories
import br.com.hurbandroidchallenge.presentation.compose.components.item_model.CategorySmallItems
import br.com.hurbandroidchallenge.presentation.compose.navigation.Screens
import org.koin.androidx.compose.getViewModel

@Composable
fun LastSeenMainScreen(
    navHostController: NavHostController,
    viewModel: LastSeenViewModel = getViewModel(),
    update: MutableState<Boolean>,
) {
    LaunchedEffect(Unit) {
        if (update.value) {
            update.value = false
            viewModel.loadLastSeen()
        }
    }
    val lastSeenUI = viewModel.lastSeenUI.value
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Column(
            modifier = Modifier.padding(all = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Last seen", style = MaterialTheme.typography.headlineSmall)
            CategorySmallItems(
                name = Categories.Characters.title,
                itemList = lastSeenUI.characters,
                onClick = { url ->
                    navHostController.navigate(Screens.CharacterDetail.routeWithArgument(url))
                },
                listState = viewModel.charactersState.collectAsStateWithLifecycle().value
            )
            CategorySmallItems(
                name = Categories.Films.title,
                itemList = lastSeenUI.films,
                onClick = { url ->
                    navHostController.navigate(Screens.FilmDetail.routeWithArgument(url))
                },
                listState = viewModel.filmsState.collectAsStateWithLifecycle().value
            )
            CategorySmallItems(
                name = Categories.Planets.title,
                itemList = lastSeenUI.planets,
                onClick = { url ->
                    navHostController.navigate(Screens.PlanetDetail.routeWithArgument(url))
                },
                listState = viewModel.planetsState.collectAsStateWithLifecycle().value
            )
        }
    }
}