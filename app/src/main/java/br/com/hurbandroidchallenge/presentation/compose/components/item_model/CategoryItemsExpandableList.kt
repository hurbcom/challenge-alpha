package br.com.hurbandroidchallenge.presentation.compose.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.hurbandroidchallenge.presentation.compose.navigation.Screens
import br.com.hurbandroidchallenge.presentation.compose.widgets.image.SmallCategoryItemImage
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.error.DefaultErrorText
import br.com.hurbandroidchallenge.presentation.compose.widgets.state.loading.DefaultLoading
import br.com.hurbandroidchallenge.presentation.model.SmallItemModel
import br.com.hurbandroidchallenge.presentation.model.StateUI

@Composable
fun CategoryItemsExpandableList(
    name: String,
    listState: StateUI<List<SmallItemModel>>,
    navHostController: NavHostController,
) {
    DefaultExpandableCard(name = name) {
        when (listState) {
            is StateUI.Error -> DefaultErrorText(message = listState.message)
            is StateUI.Idle -> Unit
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
                                navHostController.navigate(
                                    Screens.FilmDetail.routeWithArgument(item.url)
                                )
                            }
                        )
                    }
                }
            }
            is StateUI.Processing -> DefaultLoading(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}