package br.com.hurbandroidchallenge.presentation.compose.components.lazy_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import br.com.hurbandroidchallenge.domain.model.ItemModel
import br.com.hurbandroidchallenge.presentation.compose.components.card.ItemCard

@Composable
fun ItemList(
    categoryItems: List<ItemModel>,
    onItemClick: () -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(categoryItems) { item ->
            val shape = MaterialTheme.shapes.large
            ItemCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape)
                    .clickable {
                        onItemClick()
                    },
                itemModel = item,
                shape = shape
            )
        }
    }
}