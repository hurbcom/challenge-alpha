package br.com.hurbandroidchallenge.presentation.compose.components.lazy_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import br.com.hurbandroidchallenge.domain.model.ItemModel
import br.com.hurbandroidchallenge.presentation.compose.components.item_card.ItemCard

@Composable
fun GridItemList(
    categoryItems: List<ItemModel>,
    onItemClick: () -> Unit,
    aspectRatio: Float
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(categoryItems) { category ->
            ItemCard(
                modifier = Modifier
                    .clickable {
                        onItemClick()
                    },
                title = category.name,
                image = category.image,
                contentScale = ContentScale.FillWidth,
                aspectRadio = aspectRatio
            )
        }
    }
}