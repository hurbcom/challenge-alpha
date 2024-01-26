package com.vdemelo.starwarswiki.ui.screens.planets.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vdemelo.starwarswiki.R
import com.vdemelo.starwarswiki.domain.entity.model.Planet
import com.vdemelo.starwarswiki.ui.components.ImageLoader
import com.vdemelo.starwarswiki.ui.components.RetrySection
import com.vdemelo.starwarswiki.ui.components.SearchBar
import com.vdemelo.starwarswiki.ui.nav.buildPlanetDetailsRoute
import org.koin.androidx.compose.getViewModel

@Composable
fun PlanetListScreen(
    navController: NavController,
    viewModel: PlanetListViewModel = getViewModel()
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Surface(
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.star_wars_logo),
                    contentDescription = stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .heightIn(min = 0.dp, max = 140.dp)
                )
            }
            SearchBar(
                hint = stringResource(id = R.string.list_screen_search_hint),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onSearch = {
                    viewModel.loadPlanetsPaginated(search = it)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            SpeciesList(navController = navController)
        }
    }
}

@Composable
fun SpeciesList(
    navController: NavController,
    viewModel: PlanetListViewModel = getViewModel()
) {
    val speciesList by remember { viewModel.list }
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        val itemCount =
            if (speciesList.size % 2 == 0) speciesList.size / 2
            else speciesList.size / 2 + 1

        items(itemCount) {
            val hasScrolledDown = it >= itemCount - 1
            if (hasScrolledDown && !endReached) {
                viewModel.loadPlanetsPaginated()
            }
            PlanetsRow(
                rowIndex = it,
                planetsList = speciesList,
                navController = navController
            )
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
        if (loadError.isNotEmpty()) {
            RetrySection(
                error = loadError,
                onRetry = {
                    viewModel.loadPlanetsPaginated()
                }
            )
        }
    }
}

@Composable
fun PlanetItem(
    planet: Planet,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PlanetListViewModel = getViewModel()
) {
    val id = planet.url?.let { viewModel.getPlanetId(it) }
    val imageUrl = id?.let { viewModel.getPlanetImageUrl(it) }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(MaterialTheme.colorScheme.surface)
            .clickable {
                id?.run {
                    navController.navigate(
                        route = buildPlanetDetailsRoute(id = id.toString())
                    )
                }
            }
    ) {
        Column {
            ImageLoader(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .size(120.dp),
                url = imageUrl,
                contentDescription = planet.name
                    ?: stringResource(id = R.string.details_screen_image_content_description)
            )
            Text(
                text = planet.name
                    ?: stringResource(id = R.string.list_screen_name_label),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun PlanetsRow(
    rowIndex: Int,
    planetsList: List<Planet>,
    navController: NavController
) {
    Column {
        Row {
            PlanetItem(
                planet = planetsList[rowIndex * 2],
                navController = navController,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            if (planetsList.size >= rowIndex * 2 + 2) {
                PlanetItem(
                    planet = planetsList[rowIndex * 2 + 1],
                    navController = navController,
                    modifier = Modifier.weight(1f)
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
