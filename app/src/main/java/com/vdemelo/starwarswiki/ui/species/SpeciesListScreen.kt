package com.vdemelo.starwarswiki.ui.species

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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.request.ImageRequest
import com.vdemelo.starwarswiki.R
import com.vdemelo.starwarswiki.domain.entity.model.ItemsImageUrl
import com.vdemelo.starwarswiki.domain.entity.model.Species
import com.vdemelo.starwarswiki.domain.entity.model.getImageUrl
import com.vdemelo.starwarswiki.ui.HomeViewModel
import com.vdemelo.starwarswiki.ui.components.ImageLoader
import com.vdemelo.starwarswiki.ui.components.SearchBar
import com.vdemelo.starwarswiki.ui.details.DetailsScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun SpeciesListScreen(navController: NavController) {
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
                    //TODO recebe string e pesquisa
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
    viewModel: SpeciesViewModel = getViewModel()
) {
    val speciesList by remember { viewModel.speciesList }
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
                viewModel.loadSpeciesPaginated()
            }
            SpeciesRow(
                rowIndex = it,
                speciesList = speciesList,
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
                    viewModel.loadSpeciesPaginated()
                }
            )
        }
    }

}

@Composable
fun SpeciesItem(
    species: Species,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SpeciesViewModel = getViewModel()
) {
    val defaultDominantColor = MaterialTheme.colorScheme.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        dominantColor,
                        defaultDominantColor
                    )
                )
            )
            .clickable {
//                navController.navigate(
//                    "pokemon_detail_screen/${dominantColor.toArgb()}/${entry.pokemonName}"
//                )
            }
    ) {
        Column {
            val imageUrl = viewModel.getSpeciesImageUrl(species.url)
            ImageLoader(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .heightIn(min = 0.dp, 40.dp),
                url = imageUrl,
                contentDescription = stringResource(
                    id = R.string.details_screen_image_content_description
                )
            )
//            CoilImage(
//                request = ImageRequest.Builder(LocalContext.current)
//                    .data(entry.imageUrl)
//                    .target { drawable ->
//                        viewModel.calculateDominantColor(drawable) { color ->
//                            dominantColor = color
//                        }
//                    }
//                    .build(),
//                contentDescription = entry.pokemonName,
//                fadeIn = true,
//                modifier = Modifier
//                    .size(120.dp)
//                    .align(Alignment.CenterHorizontally)
//            ) {
//                CircularProgressIndicator(
//                    color = MaterialTheme.colorScheme.primary,
//                    modifier = Modifier.scale(0.5f)
//                )
//            }
            Text(
                text = species.name ?: stringResource(
                    id = R.string.species_list_screen_default_name
                ),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun SpeciesRow(
    rowIndex: Int,
    speciesList: List<Species>,
    navController: NavController
) {
    Column {
        Row {
            SpeciesItem(
                species = speciesList[rowIndex * 2],
                navController = navController,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            if (speciesList.size >= rowIndex * 2 + 2) {
                SpeciesItem(
                    species = speciesList[rowIndex * 2 + 1],
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

@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(text = error, color = Color.Red, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.common_retry))
        }
    }
}
