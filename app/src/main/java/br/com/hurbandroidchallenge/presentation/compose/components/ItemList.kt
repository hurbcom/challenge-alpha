package br.com.hurbandroidchallenge.presentation.compose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.hurbandroidchallenge.presentation.model.Categories

@Composable
fun ItemList(
    categoryItems: List<Categories>,
    aspectRatio: Float,
    onItemClick: (route: String) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(categoryItems) { category ->
            ItemCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(category.route)
                    },
                title = stringResource(id = category.nameRes),
                image = category.image,
                aspectRadio = aspectRatio
            )
        }
    }
}