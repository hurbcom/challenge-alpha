package com.example.hurb_challenge.app.presentation.filmdetails

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hurb_challenge.R
import com.example.hurb_challenge.app.domain.model.CardItem
import com.example.hurb_challenge.app.domain.model.Character
import com.example.hurb_challenge.app.domain.model.Film
import com.example.hurb_challenge.app.presentation.common.composable.AsyncImageLoader
import com.example.hurb_challenge.app.presentation.common.composable.Card
import com.example.hurb_challenge.app.presentation.common.Error
import com.example.hurb_challenge.app.presentation.common.composable.ErrorMessage
import com.example.hurb_challenge.app.presentation.common.Loaded
import com.example.hurb_challenge.app.presentation.common.Loading
import com.example.hurb_challenge.app.presentation.common.composable.CircularProgress
import com.example.hurb_challenge.app.presentation.common.State
import com.example.hurb_challenge.app.presentation.common.composable.TextDescription

@Composable
fun FilmDetailsScreen(
    film: Film,
    viewModel: FilmDetailsViewModel,
    modifier: Modifier,
    onRetry: () -> Unit
) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.loadCharactersByUrl(film.charactersUrl)
    }
    val scrollState = rememberScrollState()
    val state by viewModel.charactersStateFlow.collectAsState()

    Box(modifier = modifier) {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            ImageSection(url = film.getImageUrl())
            FilmDetailsSection(film = film)
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            FilmCharactersSection(
                state = state,
                onRetry = onRetry::invoke
            )
        }
    }
}

@Composable
fun FilmDetailsSection(film: Film) {
    TextDescription(
        title = stringResource(id = R.string.movie_title),
        subtitle = film.title
    )
    TextDescription(
        title = stringResource(id = R.string.episode_number),
        subtitle = film.episode.toString()
    )
    TextDescription(
        title = stringResource(id = R.string.directed_by),
        subtitle = film.director
    )
    TextDescription(
        title = stringResource(id = R.string.release_date),
        subtitle = film.releaseDate
    )
    TextDescription(
        title = stringResource(id = R.string.opening_crawl),
        subtitle = film.openingCrawl
    )
}

@Composable
fun ImageSection(url: String) {
    AsyncImageLoader(
        url = url,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f),
        loadingContent = {
            CircularProgress(modifier = Modifier
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
fun FilmCharactersSection(
    state: State<List<CardItem<Character>>>,
    onRetry: () -> Unit
    ) {
    Box {
        Column {
            Text(
                text = stringResource(id = R.string.characters),
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 10.dp),
                fontSize = 16.sp,
                color = Color(0xFF818181),
                style = MaterialTheme.typography.titleMedium
            )
            when (state) {
                is Loaded -> {
                    LazyRow(
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        val items = state.value
                        items(items.size) { index ->
                            Card(cardItem = items[index], onItemClicked = {})
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
