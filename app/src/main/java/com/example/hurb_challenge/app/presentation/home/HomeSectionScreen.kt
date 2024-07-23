package com.example.hurb_challenge.app.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.hurb_challenge.app.domain.model.CardItem
import com.example.hurb_challenge.app.presentation.common.composable.CircularProgress
import com.example.hurb_challenge.app.presentation.common.composable.Card
import com.example.hurb_challenge.app.presentation.common.composable.ErrorMessage

@Composable
fun <T> HomeSectionScreen(
    cardItem: LazyPagingItems<CardItem<T>>,
    onRetry: () -> Unit,
    onItemClicked: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        cardItem.apply {
            when (loadState.refresh) {
                is LoadState.Loading -> {
                    CircularProgress(
                        Modifier
                            .align(Alignment.Center)
                            .size(50.dp)
                    )
                }

                is LoadState.Error -> {
                    val error = cardItem.loadState.refresh as LoadState.Error
                    ErrorMessage(
                        message = error.error.localizedMessage ?: "Unknown Error",
                        onClick = onRetry,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }

                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.SpaceAround,
                    ) {
                        items(cardItem.itemCount) {index ->
                            Card(
                                cardItem = cardItem[index]!!,
                                onItemClicked = onItemClicked
                            )
                        }
                        if (cardItem.loadState.append is LoadState.Loading) {
                            item(span = {
                                GridItemSpan(2)
                            }) {
                                CircularProgress(
                                    Modifier.fillMaxSize()
                                        .align(Alignment.Center)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
