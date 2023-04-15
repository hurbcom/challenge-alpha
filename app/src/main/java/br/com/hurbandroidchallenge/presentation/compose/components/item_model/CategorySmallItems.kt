package br.com.hurbandroidchallenge.presentation.compose.components.item_model

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.hurbandroidchallenge.presentation.compose.widgets.image.SmallCategoryItemImage
import br.com.hurbandroidchallenge.presentation.compose.widgets.image.SmallCategoryItemPlaceholder
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.error.DefaultErrorText
import br.com.hurbandroidchallenge.presentation.model.SmallItemModel
import br.com.hurbandroidchallenge.presentation.model.StateUI

@Composable
fun CategorySmallItems(
    name: String,
    listState: StateUI<List<SmallItemModel>>,
    onClick: (url: String) -> Unit,
) {
    Card {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 16.dp, end = 16.dp, start = 16.dp),
                text = name,
                style = MaterialTheme.typography.titleLarge
            )
            when (listState) {
                is StateUI.Error -> DefaultErrorText(message = listState.message)
                is StateUI.Idle -> {
                    LazyRow{
                        items(10) {
                            SmallCategoryItemPlaceholder()
                        }
                    }
                }
                is StateUI.Processed -> {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(all = 16.dp)
                    ) {
                        items(listState.data) { item ->
                            SmallCategoryItemImage(
                                text = item.name,
                                textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                image = item.image,
                                onClick = {
                                    onClick(item.url)
                                }
                            )
                        }
                    }
                }
                is StateUI.Processing -> {
                    LazyRow{
                        items(10) {
                            SmallCategoryItemPlaceholder()
                        }
                    }
                }
            }
        }
    }
}