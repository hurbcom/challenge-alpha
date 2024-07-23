package com.example.hurb_challenge.app.presentation.characterdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hurb_challenge.R
import com.example.hurb_challenge.app.core.decodeUrl
import com.example.hurb_challenge.app.domain.model.CardItem
import com.example.hurb_challenge.app.domain.model.Character
import com.example.hurb_challenge.app.domain.model.Starship
import com.example.hurb_challenge.app.domain.model.Vehicle
import com.example.hurb_challenge.app.presentation.common.State
import com.example.hurb_challenge.app.presentation.common.Error
import com.example.hurb_challenge.app.presentation.common.Loaded
import com.example.hurb_challenge.app.presentation.common.Loading
import com.example.hurb_challenge.app.presentation.common.composable.CircularProgress
import com.example.hurb_challenge.app.presentation.common.composable.AsyncImageLoader
import com.example.hurb_challenge.app.presentation.common.composable.Card
import com.example.hurb_challenge.app.presentation.common.composable.ErrorMessage
import com.example.hurb_challenge.app.presentation.common.composable.TextDescription

@Composable
fun CharacterDetailsScreen(
    character: Character,
    viewModel: CharacterDetailsViewModel,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.loadCharacterVehicles(character.vehiclesUrl)
        viewModel.loadCharacterStarships(character.starshipsUrl)
    }

    val scrollState = rememberScrollState()
    val vehiclesState = viewModel.characterVehiclesStateFlow.collectAsState()
    val starshipsState = viewModel.characterStarshipsStateFlow.collectAsState()

    Box(modifier) {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            ImageSection(url = character.getImageUrl())
            CharacterDetailsSection(character = character)
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            CharacterVehicles(
                state = vehiclesState.value,
                onRetry = { viewModel.loadCharacterVehicles(character.vehiclesUrl) }
            )
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            CharacterStarships(
                state = starshipsState.value,
                onRetry = { viewModel.loadCharacterStarships(character.starshipsUrl) }
            )
        }
    }
}

@Composable
fun ImageSection(url: String) {
    AsyncImageLoader(
        url = url,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f),
        loadingContent = {
            CircularProgress(
                Modifier
                    .size(60.dp)
                    .align(Alignment.Center)
            )
        },
        failureContent = {
            Image(
                painter = painterResource(id = R.drawable.ic_img_placeholder),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth(),
                contentDescription = null
            )
        },
        loadedContent = { painter ->
            Image(
                painter = painter,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(),
                contentDescription = null
            )
        }
    )
}

@Composable
fun CharacterDetailsSection(character: Character) {
    TextDescription(
        title = stringResource(id = R.string.character_name),
        subtitle = character.name
    )
    TextDescription(
        title = stringResource(id = R.string.character_gender),
        subtitle = character.gender.decodeUrl()
    )
    TextDescription(
        title = stringResource(id = R.string.character_birth_year),
        subtitle = character.birthYear
    )
    TextDescription(
        title = stringResource(id = R.string.character_height),
        subtitle = "${character.height} cm"
    )
}

@Composable
fun CharacterVehicles(
    state: State<List<CardItem<Vehicle>>>,
    onRetry: () -> Unit
) {
    Box {
        Column(Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string.vehicles),
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 10.dp),
                fontSize = 16.sp,
                color = Color(0xFF818181),
                style = MaterialTheme.typography.titleMedium
            )
            when (state) {
                is Loaded -> {
                    if (state.value.isEmpty()) {
                        Text(
                            text = stringResource(id = R.string.no_item_found),
                            modifier = Modifier
                                .padding(horizontal = 6.dp, vertical = 10.dp)
                                .align(Alignment.CenterHorizontally),
                            fontSize = 16.sp,
                            color = Color.Black,
                            style = MaterialTheme.typography.titleMedium
                        )
                    } else {
                        LazyRow(
                            contentPadding = PaddingValues(8.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                        ) {
                            val items = state.value
                            items(items) { cardItem ->
                                Card(cardItem = cardItem, onItemClicked = {})
                            }
                        }
                    }
                }
                is Error -> {
                    ErrorMessage(
                        message = state.message,
                        modifier = Modifier.padding(vertical = 8.dp),
                        onClick = onRetry::invoke
                    )
                }
                is Loading -> {
                    CircularProgress(
                        Modifier
                            .fillMaxSize()
                            .padding(vertical = 10.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
                else -> { }
            }
        }
    }
}

@Composable
fun CharacterStarships(
    state: State<List<CardItem<Starship>>>,
    onRetry: () -> Unit
) {
    Box {
        Column(Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string.starships),
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 10.dp),
                fontSize = 16.sp,
                color = Color(0xFF818181),
                style = MaterialTheme.typography.titleMedium
            )
            when (state) {
                is Loaded -> {
                    if (state.value.isEmpty()) {
                        Text(
                            text = stringResource(id = R.string.no_item_found),
                            modifier = Modifier
                                .padding(horizontal = 6.dp, vertical = 10.dp)
                                .align(Alignment.CenterHorizontally),
                            fontSize = 16.sp,
                            color = Color.Black,
                            style = MaterialTheme.typography.titleMedium
                        )
                    } else {
                        LazyRow(
                            contentPadding = PaddingValues(8.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                        ) {
                            val items = state.value
                            items(items) { cardItem ->
                                Card(cardItem = cardItem, onItemClicked = {})
                            }
                        }
                    }
                }
                is Error -> {
                    ErrorMessage(
                        message = state.message,
                        modifier = Modifier.padding(vertical = 8.dp),
                        onClick = onRetry::invoke
                    )
                }
                is Loading -> {
                    CircularProgress(
                        Modifier
                            .fillMaxSize()
                            .padding(vertical = 10.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
                else -> { }
            }
        }
    }
}
